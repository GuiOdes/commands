package br.com.sapiencia.command.api.response

import br.com.sapiencia.command.database.entity.ItemComanda
import java.math.BigDecimal
import java.util.UUID

data class ItemComandaResponse(
    val produtoId: UUID? = null,
    val funcionarioResponsavel: UUID? = null,
    val preco: BigDecimal,
    val quantidade: Int,
    val valorParcial: BigDecimal
) {

    companion object {
        fun of(itemComanda: ItemComanda) = ItemComandaResponse(
            produtoId = itemComanda.id.produto.id,
            funcionarioResponsavel = itemComanda.funcionario.id,
            preco = itemComanda.id.produto.preco,
            quantidade = itemComanda.quantidade,
            valorParcial = itemComanda.valorTotal
        )
    }
}
