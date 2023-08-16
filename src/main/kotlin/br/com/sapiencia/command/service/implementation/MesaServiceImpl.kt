package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.response.MesaResponse
import br.com.sapiencia.command.database.entity.Mesa
import br.com.sapiencia.command.database.repository.MesaRepository
import br.com.sapiencia.command.exception.NotFoundException
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
        val mesa = mesaRepository.procurarPorId(id) ?: throw NotFoundException(Mesa::class)
        return MesaResponse(mesa.id, comandaService.existeComandaAtivaPorMesa(id))
    }
}
