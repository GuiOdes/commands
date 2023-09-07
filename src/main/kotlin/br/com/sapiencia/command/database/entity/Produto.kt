package br.com.sapiencia.command.database.entity

import br.com.sapiencia.command.model.ProdutoModel
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Min
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.util.UUID

@Entity
@Table(name = "produto")
data class Produto(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,
    val nome: String,
    val preco: BigDecimal,
    @Min(0)
    val estoque: Long
) {

    fun toModel() = ProdutoModel(
        id = id,
        nome = nome,
        preco = preco,
        estoque = estoque
    )

    companion object {
        fun of(produtoModel: ProdutoModel) = Produto(
            id = produtoModel.id,
            nome = produtoModel.nome,
            preco = produtoModel.preco,
            estoque = produtoModel.estoque
        )
    }
}
