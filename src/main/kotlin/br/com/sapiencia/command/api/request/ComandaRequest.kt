package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.ComandaModel

class ComandaRequest(
    val nomeResponsavel: String,
    val numeroMesa: Long
) {
    fun toModel() = ComandaModel(
        nomeResponsavel = nomeResponsavel,
        numeroMesa = numeroMesa
    )
}
