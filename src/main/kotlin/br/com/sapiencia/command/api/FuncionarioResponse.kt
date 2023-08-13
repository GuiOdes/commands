package br.com.sapiencia.command.api

import br.com.sapiencia.command.enums.PrivilegioEnum
import java.util.UUID

data class FuncionarioResponse(
    val id: UUID? = null,
    val matricula: String,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val cargo: String,
    val privilegio: PrivilegioEnum,
    val nomeUsuario: String
)
