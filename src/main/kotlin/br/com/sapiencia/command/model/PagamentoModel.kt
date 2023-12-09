package br.com.sapiencia.command.model

import java.math.BigDecimal
import java.util.UUID

data class PagamentoModel(
    val id: UUID? = null,
    val valorPago: BigDecimal,
    val meioPagamento: MeioPagamentoModel,
    val comanda: ComandaModel
)
