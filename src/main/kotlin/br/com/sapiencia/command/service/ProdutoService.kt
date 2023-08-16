package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.AlterarEstoqueProdutoRequest
import br.com.sapiencia.command.api.request.ProdutoRequest
import br.com.sapiencia.command.model.ProdutoModel

interface ProdutoService {
    fun salvar(request: ProdutoRequest): ProdutoModel
    fun listarTodos(): List<ProdutoModel>
    fun alterarEstoque(alterarEstoqueProdutoRequest: AlterarEstoqueProdutoRequest)
}
