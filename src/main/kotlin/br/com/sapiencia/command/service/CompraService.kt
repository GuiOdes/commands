package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.AlterarDescontoRequest
import br.com.sapiencia.command.api.request.PagarRequest
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.CompraModel
import java.util.UUID

interface CompraService {
    fun salvar(pagarRequest: PagarRequest): ComandaModel

}
