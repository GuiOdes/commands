package br.com.sapiencia.command.model

import br.com.sapiencia.command.enums.PrivilegioEnum
import java.util.UUID

data class FuncionarioModel(
    val id: UUID? = null,
    val matricula: String,
    val nome: String,
    val cpf: String,
    val telefone: String,
    val email: String,
    val deletado: Boolean = false,
    val cargo: CargoModel,
    val privilegio: PrivilegioEnum,
    val login: LoginModel
)
