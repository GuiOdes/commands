package br.com.sapiencia.command.builder

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.model.LoginModel
import java.util.UUID

object LoginBuilder {

    fun loginRequest(
        usuario: String = "user",
        senha: String = "pass"
    ) = CriarFuncionarioRequest.LoginRequest(
        usuario = usuario,
        senha = senha
    )

    fun loginModel(
        id: UUID? = null,
        usuario: String = "user",
        senha: String = "pass"
    ) = LoginModel(
        id = id,
        usuario = usuario,
        senha = senha
    )
}
