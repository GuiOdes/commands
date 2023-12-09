package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.AlterarDescontoRequest
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.database.repository.PagamentoRepository
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.PagamentoModel
import br.com.sapiencia.command.service.ComandaService
import br.com.sapiencia.command.service.PagamentoService
import org.springframework.stereotype.Service

@Service
class PagamentoServiceImpl(

    private val comandaService: ComandaService,
    private val pagamentoRepository: PagamentoRepository
) : PagamentoService {
    override fun adicionarPagamento(pagamentoModel: PagamentoModel): PagamentoModel {
        return pagamentoRepository.salvar(pagamentoModel)
    }

    override fun editarDesconto(alterarDescontoRequest: AlterarDescontoRequest): ComandaModel {
        var comandaModel = comandaService.procurarComandaPorId(alterarDescontoRequest.comanda)
        comandaModel!!.desconto = alterarDescontoRequest.desconto
        return comandaService.editarComanda(comandaModel)
    }
}
