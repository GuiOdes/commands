package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.Produto
import br.com.sapiencia.command.database.repository.ProdutoRepository
import br.com.sapiencia.command.database.repository.data.ProdutoJpaRepository
import br.com.sapiencia.command.model.ProdutoModel
import org.springframework.stereotype.Component

@Component
class ProdutoRepositoryImpl(
    private val produtoJpaRepository: ProdutoJpaRepository
) : ProdutoRepository {
    override fun salvar(produtoModel: ProdutoModel) = produtoJpaRepository.save(Produto.of(produtoModel)).toModel()

    override fun listarTodos() = produtoJpaRepository.findAll().map { it.toModel() }
}
