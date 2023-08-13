package br.com.sapiencia.command.model

import java.math.BigDecimal
import java.util.UUID

data class ProdutoModel(
    val id: UUID? = null,
    val nome: String,
    val preco: BigDecimal,
    val estoque: Long
)
