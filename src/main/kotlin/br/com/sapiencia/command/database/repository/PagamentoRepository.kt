package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.PagamentoModel

interface PagamentoRepository{
    fun salvar(pagamentoModel: PagamentoModel): PagamentoModel

}
