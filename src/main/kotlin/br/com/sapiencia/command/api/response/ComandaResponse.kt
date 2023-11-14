package br.com.sapiencia.command.api.response

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ComandaResponse(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val listaProdutos: List<ItemComandaResponse>? = emptyList(),
    val valorTotal: BigDecimal,
    val valorPago: BigDecimal,
    val ativa: Boolean
)
