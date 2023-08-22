package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.ComandaRequest
import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.request.PeriodoDeDatasRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.service.ComandaService
import org.springframework.stereotype.Service

@Service
class ComandaServiceImpl(
    private val comandaRepository: ComandaRepository
) : ComandaService {
    override fun salvar(request: ComandaRequest) = comandaRepository.salvar(request.toModel())
    override fun inserirProduto(inserirProdutoRequest: InserirProdutoRequest): ComandaResponse {
        return comandaRepository.inserirProduto(inserirProdutoRequest)
    }
    override fun procurarAtivaPorMesa(mesa: Long): ComandaResponse {
        return comandaRepository.procurarAtivaPorMesa(mesa) ?: throw NaoEncontradoException(Comanda::class)
    }
    override fun procurarPorPeriodo(periodoDeDatasRequest: PeriodoDeDatasRequest): List<ComandaModel> {
        return comandaRepository.procurarPorPeriodo(periodoDeDatasRequest.dataInicial, periodoDeDatasRequest.dataFinal)
    }

    override fun existeComandaAtivaPorMesa(mesa: Long): Boolean {
        return comandaRepository.existeComandaAtivaPorMesa(mesa)
    }
}
