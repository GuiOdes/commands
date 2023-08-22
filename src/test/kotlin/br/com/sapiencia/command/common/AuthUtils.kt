package br.com.sapiencia.command.common

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders

object AuthUtils {

    fun httpEntityOf(request: Any?, token: String): HttpEntity<Any> {
        val authHeader = HttpHeaders()
        authHeader.add("Authorization", "Bearer $token")

        return HttpEntity(request, authHeader)
    }
}