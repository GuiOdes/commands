package br.com.sapiencia.command.api

import java.util.UUID

data class FuncionarioResponse(
    val id: UUID? = null,
    val matricula: String,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val cargo: String,
    val nomeUsuario: String
)
