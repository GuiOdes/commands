package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.model.ProdutoModel
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.math.BigDecimal

@Entity
@Table(name = "item_comanda")
@SQLDelete(sql = "UPDATE item_comanda SET deletado = true WHERE id = ? AND deletado = false")
@Where(clause = "deletado = false")
data class ItemComanda(
    @EmbeddedId
    val id: ItemComandaKey,

    var quantidade: Int,

    val deletado: Boolean = false,

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    val funcionario: Funcionario
) {
    val valorTotal: BigDecimal
        get() = id.produto.preco * BigDecimal(quantidade)


    companion object {
        fun of(
            comanda: Comanda,
            produto: ProdutoModel,
            inserirProdutoRequest: InserirProdutoRequest,
            funcionario: Funcionario
        ) = ItemComanda(
            id = ItemComandaKey(
                comanda = comanda,
                produto = Produto.of(produto)
            ),
            quantidade = inserirProdutoRequest.quantidade,
            funcionario = funcionario
        )
    }
}
