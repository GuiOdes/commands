package br.com.sapiencia.command.builder

import br.com.sapiencia.command.api.request.LoginRequest
import br.com.sapiencia.command.database.entity.Login
import br.com.sapiencia.command.model.LoginModel
import java.util.UUID

object LoginBuilder {

    fun loginRequest(
        usuario: String = "user",
        senha: String = "pass"
    ) = LoginRequest(
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

    fun loginEntity(
        id: UUID? = null,
        usuario: String = "user",
        senha: String = "pass"
    ) = Login(
        id = id,
        usuario = usuario,
        senha = senha
    )
}
