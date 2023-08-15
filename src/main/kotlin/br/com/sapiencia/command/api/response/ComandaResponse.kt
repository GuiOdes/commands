package br.com.sapiencia.command.api.response

import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.ProdutoModel
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ComandaResponse(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val listaProdutos: List<ProdutoResponse>,
    val valorTotal: BigDecimal,
    val ativa: Boolean
) {

    companion object {
        fun of(
            comandaModel: ComandaModel,
            listaProdutos: List<ProdutoModel>
        ) = ComandaResponse(
            id = comandaModel.id,
            nomeResponsavel = comandaModel.nomeResponsavel,
            numeroMesa = comandaModel.numeroMesa,
            dataCriacao = comandaModel.dataCriacao,
            listaProdutos = listaProdutos.map { ProdutoResponse.of(it) },
            valorTotal = comandaModel.valorTotal,
            ativa = comandaModel.ativa
        )
    }
}
