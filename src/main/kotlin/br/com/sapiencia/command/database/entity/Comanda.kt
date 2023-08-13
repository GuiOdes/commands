package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.model.ComandaModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@Table(name = "comanda")
data class Comanda(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,

    @Column(name = "nome_responsavel")
    val nomeResponsavel: String,

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    val mesa: Mesa
) {

    fun toModel() = ComandaModel(
        id = id,
        nomeResponsavel = nomeResponsavel,
        numeroMesa = mesa.id
    )

    companion object {
        fun of(comandaModel: ComandaModel) = Comanda(
            id = comandaModel.id,
            nomeResponsavel = comandaModel.nomeResponsavel,
            mesa = Mesa(id = comandaModel.numeroMesa)
        )
    }
}
