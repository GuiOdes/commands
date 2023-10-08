package br.com.sapiencia.command.model

import br.com.sapiencia.command.database.entity.MeioPagamento
import java.math.BigDecimal
import java.util.UUID

data class PagamentoModel(
    val id: UUID? = null,
    val valorPago: BigDecimal,
    val meioPagamento: MeioPagamento
)
