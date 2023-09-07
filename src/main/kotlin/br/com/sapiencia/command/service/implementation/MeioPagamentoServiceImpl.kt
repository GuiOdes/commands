package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.database.repository.MeioPagamentoRepository
import br.com.sapiencia.command.model.MeioPagamentoModel
import br.com.sapiencia.command.service.MeioPagamentoService
import org.springframework.stereotype.Service

@Service
class MeioPagamentoServiceImpl(
    private val repository: MeioPagamentoRepository
) : MeioPagamentoService {
    override fun salvar(nomeMeioPagamento: String) = repository.salvar(nomeMeioPagamento)
    override fun listarTodos(): List<MeioPagamentoModel> = repository.listarTodos()
    override fun procurarPorNome(nomeMeioPagamento: String): MeioPagamentoModel? {
        return repository.procurarPorNome(nomeMeioPagamento)
    }
}
