package br.com.sapiencia.command.configurations.security

import br.com.sapiencia.command.api.response.AuthenticationResponse
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class JwtService {

    /**
     * hardcoded 256-bit hex formatted secret key (generated on https://allkeysgenerator.com/random/security-encryption-key-generator.aspx)
     */
    @Value("\${security-configurations.jwt.secret}")
    lateinit var secretKey: String

    @Value("\${security-configurations.jwt.expiration}")
    lateinit var expirationTime: Number

    fun extractUsername(token: String): String {
        return extractClaim(token, Claims::getSubject)
    }

    fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractAllClaims(token)
        return claimsResolver(claims)
    }

    fun generateToken(extraClaims: Map<String, Any>, userDetails: UserDetails): AuthenticationResponse {
        val authToken = Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong()))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()

        val refreshToken = Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expirationTime.toLong() * 2))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()

        return AuthenticationResponse(authToken, refreshToken)
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token) // json web signature
            .body
    }

    fun generateToken(userDetails: UserDetails): AuthenticationResponse {
        return generateToken(HashMap(), userDetails)
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

    private fun extractExpiration(token: String): Date {
        return extractClaim(token, Claims::getExpiration)
    }

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}
