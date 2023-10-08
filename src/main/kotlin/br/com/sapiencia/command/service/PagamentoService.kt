package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.AlterarDescontoRequest
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.PagamentoModel

interface PagamentoService {
    fun adicionarPagamento(pagamentoModel: PagamentoModel): PagamentoModel
    fun editarDesconto(alterarDescontoRequest: AlterarDescontoRequest): ComandaModel
}
