package br.com.sapiencia.command.database.entities

import br.com.sapiencia.command.model.CargoModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cargo")
class Cargo(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val nome: String
) {

    fun toCargoModel() = CargoModel(
        id = id,
        nome = nome
    )

    companion object {
        fun of(cargoModel: CargoModel) = Cargo(
            id = cargoModel.id,
            nome = cargoModel.nome
        )
    }
}
