package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.model.CargoModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@Table(name = "cargo")
class Cargo(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,
    val nome: String
) {

    fun toModel() = CargoModel(
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
