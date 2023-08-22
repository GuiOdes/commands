package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.request.AlterarCargoRequest
import br.com.sapiencia.command.api.request.CargoRequest
import br.com.sapiencia.command.model.CargoModel

interface CargoService {
    fun salvar(request: CargoRequest): CargoModel
    fun listarTodos(): List<CargoModel>
    fun editar(alterarCargoRequest: AlterarCargoRequest): CargoModel
}
