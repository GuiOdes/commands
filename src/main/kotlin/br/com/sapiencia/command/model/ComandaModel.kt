package br.com.sapiencia.command.model

import java.util.UUID

data class ComandaModel(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long
)
