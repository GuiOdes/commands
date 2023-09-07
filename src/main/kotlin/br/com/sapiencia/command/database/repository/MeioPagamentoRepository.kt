package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.MeioPagamentoModel

interface MeioPagamentoRepository {
    fun salvar(nomeMeioPagamento: String): MeioPagamentoModel
    fun listarTodos(): List<MeioPagamentoModel>
    fun procurarPorNome(nomeMeioPagamento: String): MeioPagamentoModel?
}
