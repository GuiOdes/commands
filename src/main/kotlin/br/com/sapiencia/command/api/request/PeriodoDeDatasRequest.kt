package br.com.sapiencia.command.api.request

import java.time.LocalDateTime

data class PeriodoDeDatasRequest(
    val dataInicial: LocalDateTime,
    val dataFinal: LocalDateTime
)
