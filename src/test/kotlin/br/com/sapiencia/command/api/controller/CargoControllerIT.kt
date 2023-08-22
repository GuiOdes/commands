package br.com.sapiencia.command.api.controller
import br.com.sapiencia.command.api.request.AlterarCargoRequest
import br.com.sapiencia.command.builder.CargoBuilder
import br.com.sapiencia.command.builder.CargoBuilder.criarCargoRequest
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.model.CargoModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class CargoControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val cargoJpaRepository: CargoJpaRepository
) : IntegrationTests() {

    @Test
    fun `Deve salvar o cargo`() {
        val request = criarCargoRequest()
        val response = testRestTemplate.postForEntity(BASE_URL, request, CargoModel::class.java)
        assertAll(
            { Assertions.assertEquals(response.statusCode, HttpStatus.CREATED) },
            { Assertions.assertNotNull(response.body) }
        )
    }

    @Test
    fun `Deve listar todos os cargos`() {
        cargoJpaRepository.save(CargoBuilder.cargoEntity())
        val response = testRestTemplate.getForEntity(BASE_URL, Array<CargoModel>::class.java)

        assertAll(
            { Assertions.assertNotNull(response.body) }
        )
    }
    @Test
    fun `Deve editar um cargo`() {
        val cargo = cargoJpaRepository.save(
            CargoBuilder.cargoEntity(
                nome = "Garsson"
            )
        )
        val request = AlterarCargoRequest(
            id = cargo.id!!,
            nome = "Garçon"
        )
        testRestTemplate.put(BASE_URL, request, CargoModel::class.java)
        val cargo2 = cargoJpaRepository.findById(cargo.id!!).get()
        assertAll(
            { Assertions.assertEquals(cargo2.nome, request.nome) }
        )
    }
    companion object {
        const val BASE_URL = "/cargo"
    }
}
