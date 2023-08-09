package br.com.sapiencia.command.builder

import br.com.sapiencia.command.model.LoginModel
import java.util.UUID

object LoginBuilder {

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
