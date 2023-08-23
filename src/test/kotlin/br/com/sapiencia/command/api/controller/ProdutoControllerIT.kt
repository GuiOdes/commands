package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.response.ProdutoResponse
import br.com.sapiencia.command.builder.CargoBuilder
import br.com.sapiencia.command.builder.FuncionarioBuilder
import br.com.sapiencia.command.builder.ProdutoBuilder.alterarEstoqueProdutoRequest
import br.com.sapiencia.command.builder.ProdutoBuilder.produtoEntity
import br.com.sapiencia.command.builder.ProdutoBuilder.produtoRequest
import br.com.sapiencia.command.common.AuthUtils.httpEntityOf
import br.com.sapiencia.command.common.IntegrationTests
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.database.repository.data.ProdutoJpaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.http.HttpMethod.PUT

class ProdutoControllerIT(
    @Autowired private val produtoJpaRepository: ProdutoJpaRepository,
    @Autowired private val testRestTemplate: TestRestTemplate,
    @Autowired private val cargoJpaRepository: CargoJpaRepository,
    @Autowired private val funcionarioJpaRepository: FuncionarioJpaRepository,
    @Autowired private val jwtService: JwtService,
) : IntegrationTests() {

    private lateinit var token: String

    @BeforeEach
    fun setup() {
        val cargo = cargoJpaRepository.save(CargoBuilder.cargoEntity(nome = "AUTH_ONLY"))
        val funcionario = funcionarioJpaRepository.save(FuncionarioBuilder.funcionarioAuthEntity(cargo = cargo))
        token = jwtService.generateToken(funcionario).authToken
    }

    @Test
    fun `Deve salvar um produto`() {
        produtoJpaRepository.save(produtoEntity())

        val request = produtoRequest()
        val response = testRestTemplate.exchange(
            BASE_URL,
            POST,
            httpEntityOf(request, token),
            ProdutoResponse::class.java
        )

        assertAll(
            { assert(response.statusCode.is2xxSuccessful) },
            { assertThat(response.body?.nome).isEqualTo(request.nome) },
            { assertThat(response.body?.estoque).isEqualTo(request.estoque) },
            { assertThat(response.body?.preco).isEqualTo(request.preco) }
        )
    }

    @Test
    fun `Deve listar todos os produtos`() {
        val produto = produtoJpaRepository.save(produtoEntity())
        val response = testRestTemplate.exchange(
            BASE_URL,
            GET,
            httpEntityOf(null, token),
            Array<ProdutoResponse>::class.java
        )

        assertAll(
            { assert(response.statusCode.is2xxSuccessful) },
            { assertThat(response.body?.size).isEqualTo(1) },
            { assertThat(response.body?.get(0)?.id).isEqualTo(produto.id) },
            { assertThat(response.body?.get(0)?.nome).isEqualTo(produto.nome) },
            { assertThat(response.body?.get(0)?.estoque).isEqualTo(produto.estoque) },
        )
    }

    @Test
    fun `Deve alterar o estoque de um produto`() {
        val produto = produtoJpaRepository.save(produtoEntity())

        val request = alterarEstoqueProdutoRequest(id = produto.id!!)

        testRestTemplate.exchange(
            BASE_URL,
            PUT,
            httpEntityOf(request, token),
            ProdutoResponse::class.java
        )

        val produtoAtualizado = produtoJpaRepository.findById(produto.id!!).get()

        assertAll(
            { assertThat(produtoAtualizado.estoque).isEqualTo(produto.estoque + request.quantidade) },
        )
    }

    companion object {
        const val BASE_URL = "/produto"
    }
}
