package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.PeriodoDeDatasRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.builder.CargoBuilder.cargoEntity
import br.com.sapiencia.command.builder.ComandaBuilder
import br.com.sapiencia.command.builder.ComandaBuilder.comandaEntity
import br.com.sapiencia.command.builder.ComandaBuilder.comandaRequest
import br.com.sapiencia.command.builder.FuncionarioBuilder.funcionarioAuthEntity
import br.com.sapiencia.command.builder.MesaBuilder.mesaEntity
import br.com.sapiencia.command.builder.ProdutoBuilder.produtoEntity
import br.com.sapiencia.command.common.AuthUtils.httpEntityOf
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.database.repository.data.MesaJpaRepository
import br.com.sapiencia.command.database.repository.data.ProdutoJpaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST

class ComandaControllerIT(
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val comandaJpaRepository: ComandaJpaRepository,
    @Autowired private val mesaJpaRepository: MesaJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository,
    @Autowired private val produtoJpaRepository: ProdutoJpaRepository,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val jwtService: JwtService
) : IntegrationTests() {

    private lateinit var token: String

    @BeforeEach
    fun setup() {
        val cargo = cargoJpaRepository.save(cargoEntity(nome = "AUTH_ONLY"))
        val funcionario = funcionarioJpaRepository.save(funcionarioAuthEntity(cargo = cargo))
        token = jwtService.generateToken(funcionario).authToken
    }

    @Test
    fun `Deve criar uma nova comanda no banco de dados`() {
        val mesaSalva = mesaJpaRepository.save(mesaEntity())
        val request = comandaRequest(numeroMesa = mesaSalva.id)

        testRestTemplate.exchange(
            BASE_URL,
            POST,
            httpEntityOf(request, token),
            ComandaResponse::class.java
        )

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
        val request = ComandaBuilder.inserirProdutoRequest(
            comandaId = comandaSalva.id!!,
            produtoId = produtoSalvo.id!!,
            quantidade = 1
        )

        val response = testRestTemplate.exchange(
            "$BASE_URL/inserir-produto",
            POST,
            httpEntityOf(request, token),
            ComandaResponse::class.java
        )

        assertAll(
            { assertThat(response.body?.listaProdutos?.size).isEqualTo(2) },
//            { assertThat(response.body?.listaProdutos?.first()?.produtoId).isEqualTo(produtoSalvo.id) },
//            {
//                assertThat(response.body?.listaProdutos?.first()?.preco?.toInt()).isEqualTo(produtoSalvo.preco.toInt())
//            },
//            { assertThat(produtoJpaRepository.findAll().first().estoque).isEqualTo(produtoSalvo.estoque - 1) },
//            { assertThat(response.body?.valorTotal?.toDouble()).isEqualTo(produtoSalvo.preco.toDouble() * request.quantidade) }
        )
    }

    @Test
    fun `Deve procurar a comanda ativa pelo numero de uma mesa`() {
        val mesa = mesaJpaRepository.save(mesaEntity())
        val comanda = comandaJpaRepository.save(comandaEntity(mesa = mesa))
        val response = testRestTemplate.exchange(
            "$BASE_URL/mesa/${mesa.id}",
            GET,
            httpEntityOf(null, token),
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
        val response = testRestTemplate.exchange(
            "$BASE_URL/periodo?dataInicial=${request.dataInicial}&dataFinal=${request.dataFinal}",
            GET,
            httpEntityOf(null, token),
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
