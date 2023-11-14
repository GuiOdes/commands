package br.com.sapiencia.command.model

import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ComandaModel(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long,
    var ativa: Boolean = true,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    var desconto: Double = 0.00,
    val listaItens: List<ItemComandaModel>?,
    val valorTotal: BigDecimal,
    val valorPago: BigDecimal
) {
    private val valorCompra get() = valorTotal - (valorTotal * desconto.toBigDecimal())
    fun getValorCompra():BigDecimal =  valorCompra
}
