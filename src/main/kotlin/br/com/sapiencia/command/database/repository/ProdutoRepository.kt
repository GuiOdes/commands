package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.ProdutoModel

interface ProdutoRepository {
    fun salvar(produtoModel: ProdutoModel): ProdutoModel
    fun listarTodos(): List<ProdutoModel>
}
