package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.ComandaModel
import java.math.BigDecimal

class ComandaRequest(
    val nomeResponsavel: String,
    val numeroMesa: Long
) {
    fun toModel() = ComandaModel(
        nomeResponsavel = nomeResponsavel,
        numeroMesa = numeroMesa,
        listaItens = null,
        listaPagamento = null
    )
}
