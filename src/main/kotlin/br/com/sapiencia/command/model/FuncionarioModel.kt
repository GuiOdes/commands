package br.com.sapiencia.command.model

import br.com.sapiencia.command.api.FuncionarioResponse
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
) {

    fun toResponse() = FuncionarioResponse(
        id = this.id,
        matricula = this.matricula,
        nome = this.nome,
        cpf = this.cpf,
        telefone = this.telefone,
        email = this.email,
        cargo = this.cargo.nome,
        privilegio = this.privilegio,
        nomeUsuario = this.login.usuario,
    )
}
