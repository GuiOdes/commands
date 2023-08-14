package br.com.sapiencia.command.api.response

import br.com.sapiencia.command.model.ProdutoModel
import java.math.BigDecimal
import java.util.UUID

data class ProdutoResponse(
    val id: UUID? = null,
    val nome: String,
    val preco: BigDecimal,
    val estoque: Long
) {

    companion object {
        fun of(produtoModel: ProdutoModel) = ProdutoResponse(
            id = produtoModel.id,
            nome = produtoModel.nome,
            preco = produtoModel.preco,
            estoque = produtoModel.estoque
        )
    }
}
