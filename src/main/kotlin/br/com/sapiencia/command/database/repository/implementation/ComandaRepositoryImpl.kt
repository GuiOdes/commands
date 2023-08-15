package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.entity.Funcionario
import br.com.sapiencia.command.database.entity.ItemComanda
import br.com.sapiencia.command.database.entity.Produto
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.database.repository.ProdutoRepository
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.database.repository.data.ItemComandaJpaRepository
import br.com.sapiencia.command.exception.NotFoundException
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.OperacaoProdutoEnum.DIMINUIR
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class ComandaRepositoryImpl(
    private val comandaJpaRepository: ComandaJpaRepository,
    private val produtoRepository: ProdutoRepository,
    private val funcionarioJpaRepository: FuncionarioJpaRepository,
    private val itemComandaJpaRepository: ItemComandaJpaRepository
) : ComandaRepository {
    override fun salvar(comandaModel: ComandaModel) = comandaJpaRepository.save(Comanda.of(comandaModel)).toModel()
    override fun procurarAtivaPorMesa(mesa: Long) = comandaJpaRepository.findByMesaIdAndAtivaIsTrue(mesa)
        .takeIf { it != null }?.let {
            val listaProdutos = it.listaItens?.map { item ->
                item.id.produto.toModel()
            }

            it.toResponse(listaProdutos ?: emptyList())
        }

    override fun procurarPorPeriodo(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<ComandaModel> {
        return comandaJpaRepository.findAllByDataCriacaoBetween(dataInicial, dataFinal)
            .map { it.toModel() }
    }

    override fun inserirProduto(inserirProdutoRequest: InserirProdutoRequest): ComandaResponse {
        val comanda = comandaJpaRepository.findById(inserirProdutoRequest.comandaId)
            .orElseThrow { NotFoundException(Comanda::class) }

        val produto = produtoRepository.procurarPorId(inserirProdutoRequest.produtoId)
            ?: throw NotFoundException(Produto::class)

        val funcionario = funcionarioJpaRepository.findById(inserirProdutoRequest.funcionarioId)
            .orElseThrow { NotFoundException(Funcionario::class) }

        val itemComanda = itemComandaJpaRepository.save(
            ItemComanda.of(
                produto = Produto.of(produto),
                comanda = comanda,
                quantidade = inserirProdutoRequest.quantidade,
                funcionario = funcionario
            )
        )

        val novaListaProdutos = comanda.listaItens!!.apply { this.add(itemComanda) }

        return comandaJpaRepository.save(
            comanda.copy(listaItens = novaListaProdutos)
        ).let { comandaSalva ->
            produtoRepository.alterarEstoque(
                inserirProdutoRequest.produtoId,
                inserirProdutoRequest.quantidade.toLong(),
                DIMINUIR
            )
            val listaProdutos = novaListaProdutos.map { item ->
                item.id.produto.toModel()
            }

            comandaSalva.toResponse(listaProdutos)
        }
    }
}
