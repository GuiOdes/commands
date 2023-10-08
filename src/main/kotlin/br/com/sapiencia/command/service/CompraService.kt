package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.AlterarDescontoRequest
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.CompraModel
import java.util.UUID

interface CompraService {
    fun salvar(comandaModel: ComandaModel): CompraModel
    fun listarCompra(compraId: UUID): CompraModel
    fun editarDesconto(alterarDescontoRequest: AlterarDescontoRequest): ComandaModel
}
