package br.com.sapiencia.command.builder

import br.com.sapiencia.command.database.entity.Produto
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
}
