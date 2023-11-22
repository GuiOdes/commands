package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.api.response.ComandaResponse
import br.com.sapiencia.command.api.response.ItemComandaResponse
import br.com.sapiencia.command.model.ComandaModel
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
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
    @JoinColumn(name = "numero_mesa")
    val mesa: Mesa,

    val ativa: Boolean = true,

    @Column(name = "data_criacao")
    @CreationTimestamp
    val dataCriacao: LocalDateTime = LocalDateTime.now(),

    @Column(name = "desconto")
    val desconto: Double,

    @OneToMany(mappedBy = "id.comanda", cascade = [CascadeType.ALL])
    val listaItens: MutableList<ItemComanda>?,

    @OneToMany
    val listaPagamento: List<Pagamento>? = listOf()
) {

    fun toResponse() = ComandaResponse(
        id = id,
        nomeResponsavel = nomeResponsavel,
        numeroMesa = mesa.id,
        ativa = ativa,
        dataCriacao = dataCriacao,
        listaProdutos = listaItens?.map { ItemComandaResponse.of(it) },
        desconto = desconto,
        listaPagamento = listaPagamento?.map { it.toModel() }
    )

    fun toModel() = ComandaModel(
        id = id,
        nomeResponsavel = nomeResponsavel,
        numeroMesa = mesa.id,
        ativa = ativa,
        dataCriacao = dataCriacao,
        desconto = desconto,
        listaItens = listaItens,
        listaPagamento = listaPagamento
    )

    companion object {
        fun of(comandaModel: ComandaModel) = Comanda(
            id = comandaModel.id,
            nomeResponsavel = comandaModel.nomeResponsavel,
            mesa = Mesa(id = comandaModel.numeroMesa, status = true),
            ativa = comandaModel.ativa,
            dataCriacao = comandaModel.dataCriacao,
            desconto = comandaModel.desconto,
            listaItens = null
        )
    }
}
