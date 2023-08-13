package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.FuncionarioResponse
import br.com.sapiencia.command.builder.CargoBuilder
import br.com.sapiencia.command.builder.CargoBuilder.cargoEntity
import br.com.sapiencia.command.builder.FuncionarioBuilder.criarFuncionarioRequest
import br.com.sapiencia.command.builder.LoginBuilder.loginRequest
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.database.entity.Cargo
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus.CREATED

class FuncionarioControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository
) : IntegrationTests() {

    lateinit var cargo: Cargo

    @BeforeEach
    fun setup() {
        cargo = cargoJpaRepository.findByNome(CargoBuilder.cargoModel().nome)
            ?: cargoJpaRepository.save(cargoEntity(id = null))
    }

    @Test
    fun `Deve salvar funcionarios em um mesmo cargo e criar seus logins no banco de dados`() {

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

        val response = testRestTemplate.postForEntity(BASE_URL, request, FuncionarioResponse::class.java)
        val response2 = testRestTemplate.postForEntity(BASE_URL, request2, FuncionarioResponse::class.java)

        assertAll(
            { assertEquals(response.statusCode, CREATED) },
            { assertEquals(response2.statusCode, CREATED) },
            { assertNotNull(response.body) },
            { assertNotNull(response2.body) },
            { assertEquals(cargoJpaRepository.count(), 1) },
            { assertEquals(funcionarioJpaRepository.count(), 2) }
        )
    }

    @Test
    fun `Deve deletar um funcionario do banco de dados`() {

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

        val savedFuncionario = testRestTemplate.postForEntity(BASE_URL, request, FuncionarioResponse::class.java)
        testRestTemplate.delete("$BASE_URL/${savedFuncionario.body!!.id!!}")

        assertAll(
            { assertEquals(savedFuncionario.statusCode, CREATED) },
            { assertNotNull(savedFuncionario.body) },
            { assertEquals(cargoJpaRepository.count(), 1) },
            { assertEquals(funcionarioJpaRepository.count(), 0) }
        )
    }

    companion object {
        const val BASE_URL = "/funcionario"
    }
}
