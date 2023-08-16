package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.AlterarEstoqueProdutoRequest
import br.com.sapiencia.command.api.request.ProdutoRequest
import br.com.sapiencia.command.database.repository.ProdutoRepository
import br.com.sapiencia.command.service.ProdutoService
import org.springframework.stereotype.Service

@Service
class ProdutoServiceImpl(
    private val produtoRepository: ProdutoRepository
) : ProdutoService {
    override fun salvar(request: ProdutoRequest) = produtoRepository.salvar(request.toModel())

    override fun listarTodos() = produtoRepository.listarTodos()
    override fun alterarEstoque(alterarEstoqueProdutoRequest: AlterarEstoqueProdutoRequest) {
        produtoRepository.alterarEstoque(
            alterarEstoqueProdutoRequest.produtoId,
            alterarEstoqueProdutoRequest.quantidade,
            alterarEstoqueProdutoRequest.tipoAlteracaoEstoque
        )
    }
}
