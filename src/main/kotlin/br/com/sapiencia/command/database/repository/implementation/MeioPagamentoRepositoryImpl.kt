package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.api.request.MeioPagamentoRequest
import br.com.sapiencia.command.database.entity.MeioPagamento
import br.com.sapiencia.command.database.repository.MeioPagamentoRepository
import br.com.sapiencia.command.database.repository.data.MeioPagamentoJpaRepository
import br.com.sapiencia.command.model.MeioPagamentoModel

class MeioPagamentoRepositoryImpl(
    private val meioPagamentoJpaRepository: MeioPagamentoJpaRepository
):MeioPagamentoRepository {
    override fun salvar(meioPagamentoRequest: MeioPagamentoRequest) = meioPagamentoJpaRepository.save(MeioPagamento.of(meioPagamentoRequest)).toModel()
    override fun listarTodos(): List<MeioPagamentoModel> = meioPagamentoJpaRepository.findAll().map { it.toModel() }
    override fun listarPorMeioPagamento(meioPagamentoRequest: MeioPagamentoRequest): List<MeioPagamentoModel> {
        return meioPagamentoJpaRepository.findAllByName(meioPagamentoRequest).map { it!!.toModel() }
    }
}