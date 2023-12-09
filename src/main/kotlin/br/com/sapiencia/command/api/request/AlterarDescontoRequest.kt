package br.com.sapiencia.command.api.request

import java.util.UUID

data class AlterarDescontoRequest(
    val comanda: UUID,
    val desconto: Double
)
