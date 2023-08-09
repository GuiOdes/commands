package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.builder.CargoBuilder
import br.com.sapiencia.command.builder.FuncionarioBuilder
import br.com.sapiencia.command.builder.LoginBuilder
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.model.FuncionarioModel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class FuncionarioControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository
) : IntegrationTests() {

    @AfterEach
    fun tearDown() {
        cargoJpaRepository.deleteAll()
        funcionarioJpaRepository.deleteAll()
    }

    @Test
    fun `Deve salvar funcionarios em um mesmo cargo e criar seus logins no banco de dados`() {
        val cargo = cargoJpaRepository.save(CargoBuilder.cargoEntity(id = null)).toModel()

        val request = FuncionarioBuilder.funcionarioModel(
            cargo = cargo
        )

        val request2 = FuncionarioBuilder.funcionarioModel(
            cargo = cargo,
            matricula = "123",
            cpf = "792.597.730-24",
            telefone = "(63)99999-9999",
            login = LoginBuilder.loginModel(
                usuario = "user2"
            )
        )

        val response = testRestTemplate.postForEntity(BASE_URL, request, FuncionarioModel::class.java)
        val response2 = testRestTemplate.postForEntity(BASE_URL, request2, FuncionarioModel::class.java)

        assertAll(
            { assertEquals(response.statusCode, HttpStatus.CREATED) },
            { assertEquals(response2.statusCode, HttpStatus.CREATED) },
            { assertNotNull(response.body) },
            { assertNotNull(response2.body) },
            { assertEquals(cargoJpaRepository.count(), 1) },
            { assertEquals(funcionarioJpaRepository.count(), 2) }
        )
    }

    @Test
    fun `Deve deletar um funcionario do banco de dados`() {
        val cargo = cargoJpaRepository.save(CargoBuilder.cargoEntity(id = null)).toModel()

        val request = FuncionarioBuilder.funcionarioModel(
            id = null,
            cargo = cargo
        )

        val savedFuncionario = testRestTemplate.postForEntity(BASE_URL, request, FuncionarioModel::class.java)
        testRestTemplate.delete("$BASE_URL/${savedFuncionario.body!!.id!!}")

        assertAll(
            { assertEquals(savedFuncionario.statusCode, HttpStatus.CREATED) },
            { assertNotNull(savedFuncionario.body) },
            { assertEquals(cargoJpaRepository.count(), 1) },
            { assertEquals(funcionarioJpaRepository.count(), 0) }
        )
    }

    companion object {
        const val BASE_URL = "/funcionario"
    }
}
