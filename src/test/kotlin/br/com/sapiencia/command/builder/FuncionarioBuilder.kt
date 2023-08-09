package br.com.sapiencia.command.builder

import br.com.sapiencia.command.enums.PrivilegioEnum
import br.com.sapiencia.command.model.CargoModel
import br.com.sapiencia.command.model.FuncionarioModel
import br.com.sapiencia.command.model.LoginModel
import java.util.UUID

object FuncionarioBuilder {

    fun funcionarioModel(
        id: UUID? = null,
        matricula: String = "12343",
        nome: String = "Funcionario Teste",
        cpf: String = "430.788.650-02",
        telefone: String = "(62)99999-9999",
        email: String = "test@test.com",
        deletado: Boolean = false,
        cargo: CargoModel = CargoBuilder.cargoModel(id = null),
        privilegio: PrivilegioEnum = PrivilegioEnum.COMUM,
        login: LoginModel = LoginBuilder.loginModel(id = null)
    ) = FuncionarioModel(
        id = id,
        matricula = matricula,
        nome = nome,
        cpf = cpf,
        telefone = telefone,
        email = email,
        deletado = deletado,
        cargo = cargo,
        privilegio = privilegio,
        login = login
    )
}
