package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.response.MesaResponse
import br.com.sapiencia.command.database.entity.Mesa
import br.com.sapiencia.command.database.repository.MesaRepository
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.exception.OperacaoInvalidaException
import br.com.sapiencia.command.model.MesaModel
import br.com.sapiencia.command.service.ComandaService
import br.com.sapiencia.command.service.MesaService
import org.springframework.stereotype.Service

@Service
class MesaServiceImpl(
    private val mesaRepository: MesaRepository,
    private val comandaService: ComandaService
) : MesaService {
    override fun salvar(mesaModel: MesaModel) = mesaRepository.salvar(mesaModel)

    override fun procurarPorId(id: Long): MesaResponse {
        val mesa = mesaRepository.procurarPorId(id) ?: throw NaoEncontradoException(Mesa::class)
        return MesaResponse(mesa.id, comandaService.existeComandaAtivaPorMesa(id))
    }

    override fun deletarPorId(id: Long) {
        if (comandaService.existeComandaAtivaPorMesa(id)) throw OperacaoInvalidaException()
        return mesaRepository.deletarPorID(id)
    }

    override fun existeMesaAtivaPorId(id: Long): Boolean = mesaRepository.existeMesaAtivaPorId(id)
}
