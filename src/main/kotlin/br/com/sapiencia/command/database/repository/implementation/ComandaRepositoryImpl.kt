package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.entity.Funcionario
import br.com.sapiencia.command.database.entity.ItemComanda
import br.com.sapiencia.command.database.entity.Produto
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.database.repository.data.ProdutoJpaRepository
import br.com.sapiencia.command.exception.NotFoundException
import br.com.sapiencia.command.model.ComandaModel
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ComandaRepositoryImpl(
    private val comandaJpaRepository: ComandaJpaRepository,
    private val produtoJpaRepository: ProdutoJpaRepository,
    private val funcionarioJpaRepository: FuncionarioJpaRepository
) : ComandaRepository {
    override fun salvar(comandaModel: ComandaModel) = comandaJpaRepository.save(Comanda.of(comandaModel)).toModel()
    override fun procurarAtivaPorMesa(mesa: Long) = comandaJpaRepository.findByMesaIdAndAtivaIsTrue(mesa)
        .takeIf { it != null }?.let {
            val listaProdutos = it.listaItens.map { item ->
                item.id.produto.toModel()
            }

            it.toResponse(listaProdutos)
        }

    override fun procurarPorPeriodo(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<ComandaModel> {
        return comandaJpaRepository.findAllByDataCriacaoBetween(dataInicial, dataFinal)
            .map { it.toModel() }
    }

    override fun inserirProduto(inserirProdutoRequest: InserirProdutoRequest): ComandaResponse {
        val comanda = comandaJpaRepository.findById(inserirProdutoRequest.comandaId)
            .orElseThrow { NotFoundException(Comanda::class) }

        val produto = produtoJpaRepository.findById(inserirProdutoRequest.produtoId)
            .orElseThrow { NotFoundException(Produto::class) }

        val funcionario = funcionarioJpaRepository.findById(inserirProdutoRequest.funcionarioId)
            .orElseThrow { NotFoundException(Funcionario::class) }

        val itemComanda = ItemComanda.of(produto, comanda, inserirProdutoRequest.quantidade, funcionario)

        return comandaJpaRepository.save(
            comanda.copy(listaItens = comanda.listaItens + itemComanda)
        ).let { comandaSalva ->
            val listaProdutos = comandaSalva.listaItens.map { item ->
                item.id.produto.toModel()
            }

            comandaSalva.toResponse(listaProdutos)
        }
    }
}
