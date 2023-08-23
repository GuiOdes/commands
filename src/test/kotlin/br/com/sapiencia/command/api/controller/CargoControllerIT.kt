package br.com.sapiencia.command.api.controller
import br.com.sapiencia.command.api.request.AlterarCargoRequest
import br.com.sapiencia.command.builder.CargoBuilder.cargoEntity
import br.com.sapiencia.command.builder.CargoBuilder.criarCargoRequest
import br.com.sapiencia.command.builder.FuncionarioBuilder.funcionarioEntity
import br.com.sapiencia.command.common.AuthUtils.httpEntityOf
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.model.CargoModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpMethod.PUT
import org.springframework.http.HttpStatus

class CargoControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val jwtService: JwtService,
    @Autowired private val funcionarioRepository: FuncionarioJpaRepository
) : IntegrationTests() {

    private lateinit var token: String

    @BeforeEach
    fun setupAuth() {
        val cargoAuth = cargoJpaRepository.save(cargoEntity(nome = "AUTH_ONLY"))
        val funcionario = funcionarioRepository.save(funcionarioEntity(cargo = cargoAuth))
        token = jwtService.generateToken(funcionario).authToken
    }

    @Test
    fun `Deve salvar o cargo`() {
        val request = criarCargoRequest()

        val response = testRestTemplate.exchange(
            BASE_URL,
            POST,
            httpEntityOf(request, token),
            CargoModel::class.java
        )

        assertAll(
            { Assertions.assertEquals(response.statusCode, HttpStatus.CREATED) },
            { Assertions.assertNotNull(response.body) }
        )
    }

    @Test
    fun `Deve listar todos os cargos`() {
        cargoJpaRepository.save(cargoEntity())
        val response = testRestTemplate.exchange(
            BASE_URL,
            GET,
            httpEntityOf(null, token),
            Array<CargoModel>::class.java
        )

        assertAll(
            { Assertions.assertNotNull(response.body) }
        )
    }

    @Test
    fun `Deve editar um cargo`() {
        val cargo = cargoJpaRepository.save(
            cargoEntity(
                nome = "Garsson"
            )
        )
        val request = AlterarCargoRequest(
            id = cargo.id!!,
            nome = "Garçon"
        )
        testRestTemplate.exchange(
            BASE_URL,
            PUT,
            httpEntityOf(request, token),
            CargoModel::class.java
        )
        val cargo2 = cargoJpaRepository.findById(cargo.id!!).get()
        assertAll(
            { Assertions.assertEquals(cargo2.nome, request.nome) }
        )
    }
    companion object {
        const val BASE_URL = "/cargo"
    }
}
