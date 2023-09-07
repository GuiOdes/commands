package br.com.sapiencia.command.api.request

data class LoginRequest(
    val usuario: String,
    val senha: String
)
