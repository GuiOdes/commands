package com.guiodes.template.authentication

data class AuthenticationResponse(
    val authToken: String,
    val refreshToken: String
)
