package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.model.MeioPagamentoModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "meio_pagamento")
data class MeioPagamento(
    @Id
    val id: Long? = null,
    val nome: String
) {
    fun toModel() = MeioPagamentoModel(
        id = id,
        nome = nome
    )
    companion object {
        fun of(nomeMeioPagamento: String) = MeioPagamento(
            nome = nomeMeioPagamento
        )
    }
}
