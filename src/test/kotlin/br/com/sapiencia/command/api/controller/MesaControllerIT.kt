package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.response.MesaResponse
import br.com.sapiencia.command.builder.ComandaBuilder
import br.com.sapiencia.command.builder.MesaBuilder
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.MesaJpaRepository
import br.com.sapiencia.command.model.MesaModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

class MesaControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val mesaJpaRepository: MesaJpaRepository,
    @Autowired private val comandaJpaRepository: ComandaJpaRepository
) : IntegrationTests() {
    @Test
    fun `Deve salvar a Mesa`() {
        val request = MesaBuilder.mesaEntity()
        val response = testRestTemplate.postForEntity(BASE_URL, request, MesaModel::class.java)
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
        val response = testRestTemplate.getForEntity("$BASE_URL/${mesa.id}", MesaResponse::class.java)
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
        testRestTemplate.delete("$BASE_URL/${mesa.id}")
        testRestTemplate.delete("$BASE_URL/${mesa2.id}")
        assertAll(
            { assertTrue(!mesaJpaRepository.existsById(mesa.id)) },
            { assertTrue(mesaJpaRepository.existsById(mesa2.id)) },
        )
    }
    companion object {
        const val BASE_URL = "/mesa"
    }
}
