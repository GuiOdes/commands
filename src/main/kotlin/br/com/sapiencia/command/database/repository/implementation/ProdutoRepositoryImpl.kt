package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.Produto
import br.com.sapiencia.command.database.repository.ProdutoRepository
import br.com.sapiencia.command.database.repository.data.ProdutoJpaRepository
import br.com.sapiencia.command.exception.NotFoundException
import br.com.sapiencia.command.model.OperacaoProdutoEnum
import br.com.sapiencia.command.model.OperacaoProdutoEnum.AUMENTAR
import br.com.sapiencia.command.model.OperacaoProdutoEnum.DIMINUIR
import br.com.sapiencia.command.model.ProdutoModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class ProdutoRepositoryImpl(
    private val produtoJpaRepository: ProdutoJpaRepository
) : ProdutoRepository {
    override fun salvar(produtoModel: ProdutoModel) = produtoJpaRepository.save(Produto.of(produtoModel)).toModel()

    override fun listarTodos() = produtoJpaRepository.findAll().map { it.toModel() }
    override fun procurarPorId(produtoId: UUID) = produtoJpaRepository.findById(produtoId).orElseThrow {
        NotFoundException(Produto::class)
    }.toModel()

    override fun alterarEstoque(
        produtoId: UUID,
        quantidade: Long,
        operacao: OperacaoProdutoEnum
    ) = produtoJpaRepository.findById(produtoId).takeIf { it.isPresent }?.let {
        val produto = it.get()
        val novaQuantidade = when (operacao) {
            AUMENTAR -> produto.estoque + quantidade
            DIMINUIR -> produto.estoque - quantidade
        }

        produtoJpaRepository.save(produto.copy(estoque = novaQuantidade)).toModel()
    } ?: throw NotFoundException(Produto::class)
}
