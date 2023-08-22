package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.CargoModel
import java.util.UUID

data class AlterarCargoRequest(
    val id: UUID,
    val nome: String
) {
    fun toModel() = CargoModel(
        id = id,
        nome = nome
    )
}
