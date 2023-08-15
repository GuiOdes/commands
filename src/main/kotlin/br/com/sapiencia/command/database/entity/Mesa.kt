package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.model.MesaModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "mesa")
data class Mesa(
    @Id
    val id: Long
) {
    fun toModel() = MesaModel(
        id = id
    )

    companion object {
        fun of(mesaModel: MesaModel) = Mesa(
            id = mesaModel.id
        )
    }
}
