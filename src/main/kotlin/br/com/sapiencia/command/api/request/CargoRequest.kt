package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.CargoModel

data class CargoRequest(
    val nome: String
) {
    fun toModel() = CargoModel(
        nome = nome
    )
}
