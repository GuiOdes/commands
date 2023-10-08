package br.com.sapiencia.command.builder

import br.com.sapiencia.command.api.request.ComandaRequest
import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.entity.Mesa
import br.com.sapiencia.command.model.ComandaModel
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

object ComandaBuilder {

    fun comandaModel(
        id: UUID = UUID.randomUUID(),
        nomeResponsavel: String = "Roberto",
        numeroMesa: Long = 1,
        ativa: Boolean = true,
        dataCriacao: LocalDateTime = LocalDateTime.now(),
        desconto: Double = 0.00,
        valorTotal: BigDecimal = BigDecimal.ZERO
    ) = ComandaModel(
        id = id,
        nomeResponsavel = nomeResponsavel,
        numeroMesa = numeroMesa,
        ativa = ativa,
        dataCriacao = dataCriacao,
        desconto = desconto,
        valorTotal = valorTotal
    )

    fun comandaEntity(
        id: UUID = UUID.randomUUID(),
        nomeResponsavel: String = "Roberto",
        mesa: Mesa = MesaBuilder.mesaEntity(),
        ativa: Boolean = true,
        dataCriacao: LocalDateTime = LocalDateTime.now(),
    ) = Comanda(
        id = id,
        nomeResponsavel = nomeResponsavel,
        mesa = mesa,
        ativa = ativa,
        dataCriacao = dataCriacao,
        desconto = 0.00
    )

    fun comandaRequest(
        nomeResponsavel: String = "Roberto",
        numeroMesa: Long = 1
    ) = ComandaRequest(
        nomeResponsavel = nomeResponsavel,
        numeroMesa = numeroMesa
    )

    fun inserirProdutoRequest(
        comandaId: UUID = UUID.randomUUID(),
        produtoId: UUID = UUID.randomUUID(),
        quantidade: Int
    ) = InserirProdutoRequest(
        comandaId = comandaId,
        produtoId = produtoId,
        quantidade = quantidade
    )
}
