package br.com.sapiencia.command.model

import br.com.sapiencia.command.database.entity.Compra
import br.com.sapiencia.command.database.entity.MeioPagamento
import java.util.UUID

data class PagamentoModel(
    val id:UUID? = null,
    val valorPago:Double,
    val meioPagamento: MeioPagamento
)
