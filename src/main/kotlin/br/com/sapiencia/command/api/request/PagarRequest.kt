package br.com.sapiencia.command.api.request


import java.math.BigDecimal
import java.util.UUID

data class PagarRequest(
    val valorPago:BigDecimal,
    val meioPagamentoId: Long,
    val comandaId: UUID
)
