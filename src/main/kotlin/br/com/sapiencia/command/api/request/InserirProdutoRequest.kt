package br.com.sapiencia.command.api.request

import java.util.UUID

data class InserirProdutoRequest(
    val comandaId: UUID,
    val produtoId: UUID,
    val quantidade: Int,
    val funcionarioId: UUID
)
