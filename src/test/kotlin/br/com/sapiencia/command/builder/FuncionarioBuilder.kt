package br.com.sapiencia.command.builder

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.builder.CargoBuilder.cargoEntity
import br.com.sapiencia.command.builder.CargoBuilder.cargoModel
import br.com.sapiencia.command.builder.LoginBuilder.loginEntity
import br.com.sapiencia.command.builder.LoginBuilder.loginModel
import br.com.sapiencia.command.database.entity.Cargo
import br.com.sapiencia.command.database.entity.Funcionario
import br.com.sapiencia.command.database.entity.Login
import br.com.sapiencia.command.enums.PrivilegioEnum
import br.com.sapiencia.command.model.CargoModel
import br.com.sapiencia.command.model.FuncionarioModel
import br.com.sapiencia.command.model.LoginModel
import java.util.UUID

object FuncionarioBuilder {

    fun criarFuncionarioRequest(
        matricula: String = "12343",
        nome: String = "Funcionario Teste",
        cpf: String = "430.788.650-02",
        telefone: String = "(62)99999-9999",
        email: String = "test@test.com",
        cargoName: String = "Balconista",
        loginRequest: CriarFuncionarioRequest.LoginRequest = LoginBuilder.loginRequest()
    ) = CriarFuncionarioRequest(
        matricula = matricula,
        nome = nome,
        cpf = cpf,
        telefone = telefone,
        email = email,
        loginRequest = loginRequest,
        cargoName = cargoName,
    )

    fun funcionarioModel(
        id: UUID? = null,
        matricula: String = "12343",
        nome: String = "Funcionario Teste",
        cpf: String = "430.788.650-02",
        telefone: String = "(62)99999-9999",
        email: String = "test@test.com",
        deletado: Boolean = false,
        cargo: CargoModel = cargoModel(),
        privilegio: PrivilegioEnum = PrivilegioEnum.COMUM,
        login: LoginModel = loginModel()
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

    fun funcionarioEntity(
        id: UUID? = null,
        matricula: String = "12343",
        nome: String = "Funcionario Teste",
        cpf: String = "430.788.650-02",
        telefone: String = "(62)99999-9999",
        email: String = "test@test.com",
        deletado: Boolean = false,
        cargo: Cargo = cargoEntity(),
        privilegio: PrivilegioEnum = PrivilegioEnum.COMUM,
        login: Login = loginEntity()
    ) = Funcionario(
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
