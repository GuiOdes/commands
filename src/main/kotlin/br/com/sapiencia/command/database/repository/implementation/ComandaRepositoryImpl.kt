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
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.exception.OperacaoInvalidaException
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.OperacaoProdutoEnum.DIMINUIR
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.UUID

@Component
class ComandaRepositoryImpl(
    private val comandaJpaRepository: ComandaJpaRepository,
    private val produtoRepository: ProdutoRepository,
    private val funcionarioJpaRepository: FuncionarioJpaRepository
) : ComandaRepository {
    override fun salvar(comandaModel: ComandaModel) = comandaJpaRepository.save(Comanda.of(comandaModel)).toModel()
    override fun procurarAtivaPorMesa(mesa: Long) = comandaJpaRepository
        .findByMesaIdAndAtivaIsTrue(mesa)?.toResponse()

    override fun procurarPorPeriodo(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<ComandaModel> {
        return comandaJpaRepository.findAllByDataCriacaoBetween(dataInicial, dataFinal)
            .map { it.toModel() }
    }

    override fun inserirProduto(
        inserirProdutoRequest: InserirProdutoRequest,
        documentoFuncionarioResponsavel: String
    ): ComandaResponse {
        val comanda = comandaJpaRepository.findById(inserirProdutoRequest.comandaId)
            .orElseThrow { NaoEncontradoException(Comanda::class) }

        val produto = produtoRepository.procurarPorId(inserirProdutoRequest.produtoId)
            ?: throw NaoEncontradoException(Produto::class)

        if (produto.estoque < inserirProdutoRequest.quantidade) {
            throw OperacaoInvalidaException("Estoque insuficiente")
        }

        val funcionario = funcionarioJpaRepository.findByCpf(documentoFuncionarioResponsavel)
            ?: throw NaoEncontradoException(Funcionario::class)

        comanda.listaItens?.firstOrNull { it.id.produto.id == produto.id }?.let {
            it.quantidade += inserirProdutoRequest.quantidade
        } ?: comanda.listaItens!!.add(
            ItemComanda.of(
                comanda = comanda,
                produto = produto,
                inserirProdutoRequest = inserirProdutoRequest,
                funcionario = funcionario
            )
        )

        return comandaJpaRepository.save(comanda).also {
            produtoRepository.alterarEstoque(
                inserirProdutoRequest.produtoId,
                inserirProdutoRequest.quantidade.toLong(),
                DIMINUIR
            )
        }.toResponse()
    }

    override fun existeComandaAtivaPorMesa(mesa: Long): Boolean {
        return comandaJpaRepository.existsByMesaId(mesa)
    }

    override fun procurarComandaPorId(id: UUID): ComandaModel? {
        return comandaJpaRepository.findById(id).orElseThrow { NaoEncontradoException(Comanda::class) }.toModel()
    }
}
