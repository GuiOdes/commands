package br.com.sapiencia.command.database.entity

import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@Table(name = "item_comanda")
@SQLDelete(sql = "UPDATE item_comanda SET deletado = true WHERE id = ? AND deletado = false")
@Where(clause = "deletado = false")
data class ItemComanda(
    @EmbeddedId
    val id: ItemComandaKey,

    val quantidade: Int,

    val deletado: Boolean,

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    val funcionario: Funcionario
)
