package br.com.sapiencia.command.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ComandaModel(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long,
    val ativa: Boolean = true,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val valorTotal: BigDecimal = BigDecimal.ZERO
)
