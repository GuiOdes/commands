package br.com.sapiencia.command.builder

import br.com.sapiencia.command.api.request.AlterarEstoqueProdutoRequest
import br.com.sapiencia.command.api.request.ProdutoRequest
import br.com.sapiencia.command.database.entity.Produto
import br.com.sapiencia.command.model.OperacaoProdutoEnum
import java.math.BigDecimal
import java.util.UUID

object ProdutoBuilder {

    fun produtoEntity(
        id: UUID? = null,
        nome: String = "Coca-Cola",
        preco: BigDecimal = BigDecimal.TEN,
        estoque: Long = 10L
    ) = Produto(
        id = id,
        nome = nome,
        preco = preco,
        estoque = estoque
    )

    fun produtoRequest(
        nome: String = "Coca-Cola",
        preco: BigDecimal = BigDecimal.TEN,
        estoque: Long = 10L
    ) = ProdutoRequest(
        nome = nome,
        preco = preco,
        estoque = estoque
    )

    fun alterarEstoqueProdutoRequest(
        id: UUID = UUID.randomUUID(),
        estoque: Long = 10L,
        tipoAlteracaoEstoque: OperacaoProdutoEnum = OperacaoProdutoEnum.AUMENTAR
    ) = AlterarEstoqueProdutoRequest(
        produtoId = id,
        quantidade = estoque,
        tipoAlteracaoEstoque
    )
}
