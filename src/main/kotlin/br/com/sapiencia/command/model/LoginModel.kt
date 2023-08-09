package br.com.sapiencia.command.model

import java.util.UUID

data class LoginModel(
    val id: UUID? = null,
    val usuario: String,
    val senha: String
)
