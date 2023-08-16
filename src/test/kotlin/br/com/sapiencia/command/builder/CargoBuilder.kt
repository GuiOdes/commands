package br.com.sapiencia.command.builder

import br.com.sapiencia.command.api.request.CargoRequest
import br.com.sapiencia.command.database.entity.Cargo
import br.com.sapiencia.command.model.CargoModel
import java.util.UUID

object CargoBuilder {

    fun cargoModel(
        id: UUID? = null,
        nome: String = "Balconista"
    ) = CargoModel(
        id = id,
        nome = nome
    )

    fun cargoEntity(
        id: UUID? = null,
        nome: String = "Balconista"
    ) = Cargo(
        id = id,
        nome = nome
    )
    fun criarCargoRequest(
        nome: String = "Balconista"
    ) = CargoRequest(
        nome = nome
    )
}
