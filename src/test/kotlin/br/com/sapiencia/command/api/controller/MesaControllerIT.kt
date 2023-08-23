package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.response.MesaResponse
import br.com.sapiencia.command.builder.CargoBuilder
import br.com.sapiencia.command.builder.ComandaBuilder
import br.com.sapiencia.command.builder.FuncionarioBuilder
import br.com.sapiencia.command.builder.MesaBuilder
import br.com.sapiencia.command.common.AuthUtils.httpEntityOf
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.database.repository.data.MesaJpaRepository
import br.com.sapiencia.command.model.MesaModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus

class MesaControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val mesaJpaRepository: MesaJpaRepository,
    @Autowired private val comandaJpaRepository: ComandaJpaRepository,
    @Autowired private val jwtService: JwtService,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository
) : IntegrationTests() {

    private lateinit var token: String

    @BeforeEach
    fun setup() {
        val cargo = cargoJpaRepository.save(CargoBuilder.cargoEntity(nome = "AUTH_ONLY"))
        val funcionario = funcionarioJpaRepository.save(FuncionarioBuilder.funcionarioAuthEntity(cargo = cargo))
        token = jwtService.generateToken(funcionario).authToken
    }

    @Test
    fun `Deve salvar a Mesa`() {
        val request = MesaBuilder.mesaEntity()
        val response = testRestTemplate.exchange(
            BASE_URL,
            HttpMethod.POST,
            httpEntityOf(request, token),
            MesaModel::class.java
        )
        assertAll(
            { assertEquals(response.statusCode, HttpStatus.CREATED) },
            { assertNotNull(response.body) }
        )
    }

    @Test
    fun `Deve procurar mesa por id`() {
        val mesa = mesaJpaRepository.save(MesaBuilder.mesaEntity())
        comandaJpaRepository.save(ComandaBuilder.comandaEntity(mesa = mesa))
        val possuiComandaAtiva = comandaJpaRepository.existsByMesaId(mesa = mesa.id)
        val response = testRestTemplate.exchange(
            "$BASE_URL/${mesa.id}",
            HttpMethod.GET,
            httpEntityOf(null, token),
            MesaResponse::class.java
        )
        assertAll(
            { assertTrue(response.statusCode.is2xxSuccessful) },
            { assertTrue(possuiComandaAtiva) },
            { assertNotNull(response.body) }
        )
    }

    @Test
    fun `Deve deletar mesa por id`() {
        val mesa = mesaJpaRepository.save(MesaBuilder.mesaEntity())
        val mesa2 = mesaJpaRepository.save(MesaBuilder.mesaEntity(id = 2))
        comandaJpaRepository.save(ComandaBuilder.comandaEntity(mesa = mesa2))
        testRestTemplate.exchange(
            "$BASE_URL/${mesa.id}",
            HttpMethod.DELETE,
            httpEntityOf(null, token),
            Unit::class.java
        )
        testRestTemplate.exchange(
            "$BASE_URL/${mesa2.id}",
            HttpMethod.DELETE,
            httpEntityOf(null, token),
            Unit::class.java
        )
        assertAll(
            { assertTrue(!mesaJpaRepository.existsById(mesa.id)) },
            { assertTrue(mesaJpaRepository.existsById(mesa2.id)) },
        )
    }
    companion object {
        const val BASE_URL = "/mesa"
    }
}
