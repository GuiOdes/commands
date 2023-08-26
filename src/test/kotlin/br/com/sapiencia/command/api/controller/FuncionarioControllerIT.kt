package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.FuncionarioResponse
import br.com.sapiencia.command.builder.CargoBuilder.cargoEntity
import br.com.sapiencia.command.builder.FuncionarioBuilder
import br.com.sapiencia.command.builder.FuncionarioBuilder.criarFuncionarioRequest
import br.com.sapiencia.command.builder.FuncionarioBuilder.funcionarioAuthEntity
import br.com.sapiencia.command.builder.LoginBuilder.loginRequest
import br.com.sapiencia.command.common.AuthUtils.httpEntityOf
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.enums.PrivilegioEnum
import br.com.sapiencia.command.enums.PrivilegioEnum.ADMIN
import br.com.sapiencia.command.enums.PrivilegioEnum.COMUM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod.DELETE
import org.springframework.http.HttpStatus.CREATED

class FuncionarioControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository,
    @Autowired private val jwtService: JwtService
) : IntegrationTests() {

    private lateinit var token: String

    @BeforeEach
    fun setup() {
        val cargo = cargoJpaRepository.save(cargoEntity(nome = "AUTH_ONLY"))
        val funcionario = funcionarioJpaRepository.save(
            funcionarioAuthEntity(
                cargo = cargo,
                privilegio = COMUM
            )
        )
        token = jwtService.generateToken(funcionario).authToken
    }

    @Test
    fun `Deve salvar funcionarios em um mesmo cargo e criar seus logins no banco de dados`() {
        val cargo = cargoJpaRepository.save(cargoEntity())

        val request = criarFuncionarioRequest(
            cargoName = cargo.nome
        )

        val request2 = criarFuncionarioRequest(
            cargoName = cargo.nome,
            matricula = "123",
            cpf = "792.597.730-24",
            telefone = "(63)99999-9999",
            loginRequest = loginRequest(
                usuario = "user2",
                senha = "pass2"
            )
        )

        val response = testRestTemplate.postForEntity(
            "$BASE_URL/cadastro",
            request,
            FuncionarioResponse::class.java
        )
        val response2 = testRestTemplate.postForEntity(
            "$BASE_URL/cadastro",
            request2,
            FuncionarioResponse::class.java
        )

        assertAll(
            { assertEquals(response.statusCode, CREATED) },
            { assertEquals(response2.statusCode, CREATED) },
            { assertNotNull(response.body) },
            { assertNotNull(response2.body) },
            { assertEquals(cargoJpaRepository.count() - 1, 1) },
            { assertEquals(funcionarioJpaRepository.count() - 1, 2) }
        )
    }

    @Test
    fun `Deve deletar um funcionario do banco de dados`() {
        val cargo = cargoJpaRepository.save(cargoEntity())

        val request = criarFuncionarioRequest(
            cargoName = cargo.nome,
            loginRequest = loginRequest(
                usuario = "funcionario"
            ),
            matricula = "98u654",
            cpf = "29736688089",
            telefone = "(85)999999999",
            email = "funcionario@teste.com"
        )

        val savedFuncionario = testRestTemplate.postForEntity(
            "$BASE_URL/cadastro",
            request,
            FuncionarioResponse::class.java
        )

        testRestTemplate.exchange(
            "$BASE_URL/deletar/${savedFuncionario.body!!.id!!}",
            DELETE,
            httpEntityOf(null, token),
            Unit::class.java
        )

        assertAll(
            { assertEquals(savedFuncionario.statusCode, CREATED) },
            { assertNotNull(savedFuncionario.body) },
            { assertEquals(cargoJpaRepository.count() - 1, 1) },
            { assertEquals(funcionarioJpaRepository.count() - 1, 0) }
        )
    }

    companion object {
        const val BASE_URL = "/funcionario"
    }
}
