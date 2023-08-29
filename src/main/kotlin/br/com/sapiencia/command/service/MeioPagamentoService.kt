package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.MeioPagamentoRequest
import br.com.sapiencia.command.model.MeioPagamentoModel

interface MeioPagamentoService {
    fun salvar(meioPagamentoRequest: MeioPagamentoRequest):MeioPagamentoModel
    fun listarTodos(): List<MeioPagamentoModel>
    fun listarPorMeioPagamento(meioPagamentoRequest: MeioPagamentoRequest): List<MeioPagamentoModel>
}