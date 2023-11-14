package br.com.sapiencia.command.service

import br.com.sapiencia.command.model.MeioPagamentoModel

interface MeioPagamentoService {
    fun salvar(nomeMeioPagamento: String): MeioPagamentoModel
    fun listarTodos(): List<MeioPagamentoModel>
    fun procurarPorId(id: Long): MeioPagamentoModel?
}
