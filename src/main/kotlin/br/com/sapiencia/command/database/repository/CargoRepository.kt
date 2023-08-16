package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.CargoModel
interface CargoRepository {
    fun salvar(cargoModel: CargoModel): CargoModel
    fun listarTodos(): List<CargoModel>
}
