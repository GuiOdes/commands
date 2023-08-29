package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.MeioPagamentoRequest
import br.com.sapiencia.command.database.repository.MeioPagamentoRepository
import br.com.sapiencia.command.model.MeioPagamentoModel
import br.com.sapiencia.command.service.MeioPagamentoService
import org.springframework.stereotype.Service

@Service
class MeioPagamentoServiceImpl(
    private val repository: MeioPagamentoRepository
):MeioPagamentoService {
    override fun salvar(meioPagamentoRequest: MeioPagamentoRequest) = repository.salvar(meioPagamentoRequest)
    override fun listarTodos(): List<MeioPagamentoModel> = repository.listarTodos()
    override fun listarPorMeioPagamento(meioPagamentoRequest: MeioPagamentoRequest): List<MeioPagamentoModel> {
        return repository.listarPorMeioPagamento(meioPagamentoRequest)
    }
}