package br.com.sapiencia.command.model

data class ItemComandaModel(
    val produtoModel: ProdutoModel,
    val comandaModel: ComandaModel,
    val quantidade: Int,
    val deletado: Boolean,
    val funcionarioModel: FuncionarioModel
)
