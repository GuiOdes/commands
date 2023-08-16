package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.OperacaoProdutoEnum
import jakarta.validation.constraints.Min
import java.util.UUID

data class AlterarEstoqueProdutoRequest(
    val produtoId: UUID,
    @Min(1)
    val quantidade: Long,
    val tipoAlteracaoEstoque: OperacaoProdutoEnum
)
