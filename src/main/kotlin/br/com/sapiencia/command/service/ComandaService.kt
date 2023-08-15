package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.ComandaRequest
import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.model.ComandaModel
import java.time.LocalDateTime

interface ComandaService {
    fun salvar(request: ComandaRequest): ComandaModel
    fun inserirProduto(inserirProdutoRequest: InserirProdutoRequest): ComandaResponse
    fun procurarAtivaPorMesa(mesa: Long): ComandaResponse?
    fun procurarPorPeriodo(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<ComandaModel>
}
