package br.com.sapiencia.command.api.request

import br.com.sapiencia.command.model.ProdutoModel
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import java.math.BigDecimal

data class ProdutoRequest(
    @NotBlank
    val nome: String,
    @Min(0)
    val preco: BigDecimal,
    @Min(0)
    val estoque: Long
) {

    fun toModel() = ProdutoModel(
        nome = nome,
        preco = preco,
        estoque = estoque
    )
}
