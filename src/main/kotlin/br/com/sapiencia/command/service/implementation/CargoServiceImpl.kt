package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.CargoRequest
import br.com.sapiencia.command.database.repository.CargoRepository
import br.com.sapiencia.command.service.CargoService
import org.springframework.stereotype.Service

@Service
class CargoServiceImpl(
    val cargoRepository: CargoRepository
) : CargoService {
    override fun salvar(request: CargoRequest) = cargoRepository.salvar(request.toModel())
    override fun listarTodos() = cargoRepository.listarTodos()
}
