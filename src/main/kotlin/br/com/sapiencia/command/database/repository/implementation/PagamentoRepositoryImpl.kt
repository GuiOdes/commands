package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.entity.MeioPagamento
import br.com.sapiencia.command.database.entity.Pagamento
import br.com.sapiencia.command.database.repository.MeioPagamentoRepository
import br.com.sapiencia.command.database.repository.PagamentoRepository
import br.com.sapiencia.command.database.repository.data.ComandaJpaRepository
import br.com.sapiencia.command.database.repository.data.MeioPagamentoJpaRepository
import br.com.sapiencia.command.database.repository.data.PagamentoJpaRepository
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.model.PagamentoModel
import br.com.sapiencia.command.service.ComandaService
import br.com.sapiencia.command.service.MeioPagamentoService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class PagamentoRepositoryImpl(
    val pagamentoJpaRepository: PagamentoJpaRepository,
    private val comandaJpaRepository: ComandaJpaRepository,
    private val meioPagamentoJpaRepository: MeioPagamentoJpaRepository
) : PagamentoRepository {
    override fun salvar(pagamentoModel: PagamentoModel): PagamentoModel {
        val meioPagamento = meioPagamentoJpaRepository.findByIdOrNull(
            pagamentoModel.meioPagamento.id
        ) ?: throw NaoEncontradoException(MeioPagamento::class)

        val comanda = comandaJpaRepository.findByIdOrNull(
            pagamentoModel.id
        )?:throw NaoEncontradoException(Comanda::class)

        return pagamentoJpaRepository.save(Pagamento.of(
            pagamentoModel, meioPagamento = meioPagamento,
            comanda = comanda
        )).toModel()
    }
}
