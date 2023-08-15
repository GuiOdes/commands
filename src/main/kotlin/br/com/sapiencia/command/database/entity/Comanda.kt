package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.api.response.ProdutoResponse
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.ProdutoModel
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
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
    val mesa: Mesa,

    val ativa: Boolean = true,

    @Column(name = "data_criacao")
    @CreationTimestamp
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "id.comanda", fetch = FetchType.LAZY)
    val listaItens: List<ItemComanda> = emptyList()
) {
    private val valorTotal get() = listaItens.sumOf { it.valorTotal }

    fun toResponse(
        listaProdutos: List<ProdutoModel>
    ) = ComandaResponse(
        id = id,
        nomeResponsavel = nomeResponsavel,
        numeroMesa = mesa.id,
        ativa = ativa,
        dataCriacao = dataCriacao,
        listaProdutos = listaProdutos.map { ProdutoResponse.of(it) },
        valorTotal = valorTotal
    )

    fun toModel() = ComandaModel(
        id = id,
        nomeResponsavel = nomeResponsavel,
        numeroMesa = mesa.id,
        ativa = ativa,
        dataCriacao = dataCriacao,
        valorTotal = valorTotal
    )

    companion object {
        fun of(comandaModel: ComandaModel) = Comanda(
            id = comandaModel.id,
            nomeResponsavel = comandaModel.nomeResponsavel,
            mesa = Mesa(id = comandaModel.numeroMesa),
            ativa = comandaModel.ativa,
            dataCriacao = comandaModel.dataCriacao
        )
    }
}
