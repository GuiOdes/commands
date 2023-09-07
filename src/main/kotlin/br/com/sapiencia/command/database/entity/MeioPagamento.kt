package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.api.request.MeioPagamentoRequest
import br.com.sapiencia.command.model.MeioPagamentoModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "meio_pagamento")
data class MeioPagamento (
    @Id
    val id: Long,
    val nome:String
){
    fun toModel() = MeioPagamentoModel(
        id = id,
        nome = nome
    )
    companion object{
        fun of (meioPagamentoModel: MeioPagamentoRequest) = MeioPagamento(
            id = meioPagamentoModel.id,
            nome = meioPagamentoModel.nome
        )
    }
}