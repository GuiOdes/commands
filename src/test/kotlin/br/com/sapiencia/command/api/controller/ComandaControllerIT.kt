package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.PeriodoDeDatasRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.builder.CargoBuilder.cargoEntity
import br.com.sapiencia.command.builder.ComandaBuilder
import br.com.sapiencia.command.builder.ComandaBuilder.comandaEntity
import br.com.sapiencia.command.builder.ComandaBuilder.comandaRequest
import br.com.sapiencia.command.builder.FuncionarioBuilder.funcionarioEntity
import br.com.sapiencia.command.builder.MesaBuilder.mesaEntity
import br.com.sapiencia.command.builder.ProdutoBuilder.produtoEntity
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.database.repository.data.MesaJpaRepository
import br.com.sapiencia.command.database.repository.data.ProdutoJpaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate

class ComandaControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val comandaJpaRepository: ComandaJpaRepository,
    @Autowired private val mesaJpaRepository: MesaJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository,
    @Autowired private val produtoJpaRepository: ProdutoJpaRepository,
    @Autowired private val cargoJpaRepository: CargoJpaRepository
) : IntegrationTests() {

    @Test
    fun `Deve criar uma nova comanda no banco de dados`() {
        val mesaSalva = mesaJpaRepository.save(mesaEntity())
        val request = comandaRequest(numeroMesa = mesaSalva.id)

        testRestTemplate.postForEntity(BASE_URL, request, ComandaResponse::class.java)

        assertAll(
            { assertThat(comandaJpaRepository.count()).isEqualTo(1) },
            {
                assertThat(comandaJpaRepository.findAll().first().mesa.id).isEqualTo(mesaSalva.id)
                    .isEqualTo(request.numeroMesa)
            },
            { assertThat(comandaJpaRepository.findAll().first().nomeResponsavel).isEqualTo(request.nomeResponsavel) }
        )
    }

    @Test
    fun `Deve inserir e remover itens de uma comanda salva no banco de dados`() {
        val mesaSalva = mesaJpaRepository.save(mesaEntity())
        val comandaSalva = comandaJpaRepository.save(comandaEntity(mesa = mesaSalva))
        val produtoSalvo = produtoJpaRepository.save(produtoEntity())
        val cargoSalvo = cargoJpaRepository.save(cargoEntity())
        val funcionarioSalvo = funcionarioJpaRepository.save(funcionarioEntity(cargo = cargoSalvo))
        val request = ComandaBuilder.inserirProdutoRequest(
            comandaId = comandaSalva.id!!,
            produtoId = produtoSalvo.id!!,
            funcionarioId = funcionarioSalvo.id!!,
            quantidade = 1
        )

        val response = testRestTemplate.postForEntity(
            "$BASE_URL/inserir-produto",
            request,
            ComandaResponse::class.java
        )

        assertAll(
            { assertThat(response.body?.listaProdutos?.size).isEqualTo(1) },
            { assertThat(response.body?.listaProdutos?.first()?.id).isEqualTo(produtoSalvo.id) },
            {
                assertThat(response.body?.listaProdutos?.first()?.preco?.toInt()).isEqualTo(produtoSalvo.preco.toInt())
            },
            { assertThat(produtoJpaRepository.findAll().first().estoque).isEqualTo(produtoSalvo.estoque - 1) },
            { assertThat(response.body?.valorTotal?.toInt()).isEqualTo(produtoSalvo.preco.toInt() * request.quantidade) }
        )
    }

    @Test
    fun `Deve procurar a comanda ativa pelo numero de uma mesa`() {
        val mesa = mesaJpaRepository.save(mesaEntity())
        val comanda = comandaJpaRepository.save(comandaEntity(mesa = mesa))
        val response = testRestTemplate.getForEntity(
            "$BASE_URL/mesa?mesa=${mesa.id}",
            ComandaResponse::class.java
        )

        assertAll(
            { assertThat(response.body?.id).isEqualTo(comanda.id) },
            { assertThat(response.body?.numeroMesa).isEqualTo(mesa.id) },
            { assertThat(response.body?.nomeResponsavel).isEqualTo(comanda.nomeResponsavel) },
            { assertThat(response.body?.ativa).isTrue() }
        )
    }

    @Test
    fun `Deve procurar comandas de um periodo de datas`() {
        val mesa = mesaJpaRepository.save(mesaEntity())
        val comandas = listOf(
            comandaJpaRepository.save(comandaEntity(mesa = mesa)),
            comandaJpaRepository.save(comandaEntity(mesa = mesa))
        )
        val request = PeriodoDeDatasRequest(
            dataInicial = comandas.first().dataCriacao.minusDays(1),
            dataFinal = comandas.last().dataCriacao.plusDays(1)
        )
        val response = testRestTemplate.getForEntity(
            "$BASE_URL/periodo?dataInicial=${request.dataInicial}&dataFinal=${request.dataFinal}",
            Array<ComandaResponse>::class.java
        )

        assertAll(
            { assertThat(response.statusCode.is2xxSuccessful) },
            { assertThat(response.body?.size).isEqualTo(2) }
        )
    }

    companion object {
        const val BASE_URL = "/comanda"
    }
}
