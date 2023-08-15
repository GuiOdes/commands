package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.ComandaRequest
import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.exception.NotFoundException
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.service.ComandaService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ComandaServiceImpl(
    private val comandaRepository: ComandaRepository
) : ComandaService {
    override fun salvar(request: ComandaRequest) = comandaRepository.salvar(request.toModel())
    override fun inserirProduto(inserirProdutoRequest: InserirProdutoRequest): ComandaResponse {
        return comandaRepository.inserirProduto(inserirProdutoRequest)
    }
    override fun procurarAtivaPorMesa(mesa: Long): ComandaResponse {
        return comandaRepository.procurarAtivaPorMesa(mesa) ?: throw NotFoundException(Comanda::class)
    }
    override fun procurarPorPeriodo(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<ComandaModel> {
        return comandaRepository.procurarPorPeriodo(dataInicial, dataFinal)
    }
}
