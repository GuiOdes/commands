package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.model.ComandaModel
import java.time.LocalDateTime

interface ComandaRepository {
    fun salvar(comandaModel: ComandaModel): ComandaModel
    fun procurarAtivaPorMesa(mesa: Long): ComandaResponse?
    fun procurarPorPeriodo(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<ComandaModel>
    fun inserirProduto(inserirProdutoRequest: InserirProdutoRequest): ComandaResponse
    fun existeComandaAtivaPorMesa(mesa: Long): Boolean
}
