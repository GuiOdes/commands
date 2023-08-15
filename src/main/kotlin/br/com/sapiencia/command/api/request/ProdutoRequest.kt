package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.ProdutoModel
import java.math.BigDecimal

data class ProdutoRequest(
    val nome: String,
    val preco: BigDecimal,
    val estoque: Long
) {

    fun toModel() = ProdutoModel(
        nome = nome,
        preco = preco,
        estoque = estoque
    )
}
