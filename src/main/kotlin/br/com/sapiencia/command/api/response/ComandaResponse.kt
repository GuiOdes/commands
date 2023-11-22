package br.com.sapiencia.command.api.response

import br.com.sapiencia.command.model.PagamentoModel
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ComandaResponse(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val listaProdutos: List<ItemComandaResponse>? = emptyList(),
    val desconto:Double,
    val listaPagamento: List<PagamentoModel>?,
    val ativa: Boolean
){
    val valorTotal get() = listaProdutos?.sumOf { it.valorParcial}?:BigDecimal.ZERO
    val valorPago get() = listaPagamento?.sumOf{ it.valorPago }?: BigDecimal.ZERO
}
