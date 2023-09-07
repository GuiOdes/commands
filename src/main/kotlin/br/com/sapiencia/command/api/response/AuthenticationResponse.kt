package br.com.sapiencia.command.api.response

data class AuthenticationResponse(
    val authToken: String,
    val refreshToken: String
)
