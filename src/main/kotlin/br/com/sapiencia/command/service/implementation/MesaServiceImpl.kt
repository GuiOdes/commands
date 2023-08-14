package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.database.repository.MesaRepository
import br.com.sapiencia.command.model.MesaModel
import br.com.sapiencia.command.service.MesaService
import org.springframework.stereotype.Service

@Service
class MesaServiceImpl(
    private val mesaRepository: MesaRepository
) : MesaService {
    override fun salvar(mesaModel: MesaModel) = mesaRepository.salvar(mesaModel)

    override fun procurarPorId(id: Long) = mesaRepository.procurarPorId(id)
}
