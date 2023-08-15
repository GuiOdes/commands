package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.OperacaoProdutoEnum
import br.com.sapiencia.command.model.ProdutoModel
import java.util.UUID

interface ProdutoRepository {
    fun salvar(produtoModel: ProdutoModel): ProdutoModel
    fun listarTodos(): List<ProdutoModel>
    fun procurarPorId(produtoId: UUID): ProdutoModel?
    fun alterarEstoque(produtoId: UUID, quantidade: Long, operacao: OperacaoProdutoEnum): ProdutoModel
}
