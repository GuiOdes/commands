package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.model.PagamentoModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "pagamento")
data class Pagamento(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,
    val valorPago: BigDecimal,
    @ManyToOne
    @JoinColumn(name = "meio_pagamento_id")
    val meioPagamento: MeioPagamento
) {
    fun toModel() = PagamentoModel(
        id = id,
        valorPago = valorPago,
        meioPagamento = meioPagamento
    )
    companion object {
        fun of(pagamentoModel: PagamentoModel) = Pagamento(
            id = pagamentoModel.id,
            valorPago = pagamentoModel.valorPago,
            meioPagamento = pagamentoModel.meioPagamento
        )
    }
}
