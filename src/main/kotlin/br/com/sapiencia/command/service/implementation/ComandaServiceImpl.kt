package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.ComandaRequest
import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.api.request.PeriodoDeDatasRequest
import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.service.ComandaService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ComandaServiceImpl(
    private val comandaRepository: ComandaRepository,
    private val jwtService: JwtService
) : ComandaService {
    override fun salvar(request: ComandaRequest) = comandaRepository.salvar(request.toModel())

    @Suppress("MagicNumber")
    override fun inserirProduto(
        inserirProdutoRequest: InserirProdutoRequest,
        token: String
    ): ComandaResponse {
        val documentoFuncionarioResponsavel = jwtService.extractUsername(token.substring(7))

        return comandaRepository.inserirProduto(
            inserirProdutoRequest,
            documentoFuncionarioResponsavel
        )
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

    override fun procurarComandaPorId(id: UUID): ComandaModel? {
        return comandaRepository.procurarComandaPorId(id)
    }

    override fun editarComanda(comandaModel: ComandaModel): ComandaModel {
        return comandaRepository.salvar(comandaModel)
    }
}
