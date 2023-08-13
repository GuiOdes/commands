package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.model.LoginModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@Table
data class Login(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,
    val usuario: String,
    val senha: String
) {

    fun toModel() = LoginModel(
        id = id,
        usuario = usuario,
        senha = senha
    )

    companion object {
        fun of(loginModel: LoginModel) = Login(
            id = loginModel.id,
            usuario = loginModel.usuario,
            senha = loginModel.senha
        )

        fun of(
            loginRequest: CriarFuncionarioRequest.LoginRequest
        ) = Login(
            usuario = loginRequest.usuario,
            senha = loginRequest.senha
        )
    }
}
