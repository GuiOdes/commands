package br.com.sapiencia.command.service

import br.com.sapiencia.command.model.ComandaModel

interface CompraService {
    fun salvar(comandaModel: ComandaModel)
}