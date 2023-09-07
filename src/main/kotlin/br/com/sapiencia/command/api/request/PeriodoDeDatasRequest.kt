package br.com.sapiencia.command.api.request

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

data class PeriodoDeDatasRequest(
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val dataInicial: LocalDateTime,
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val dataFinal: LocalDateTime
)
