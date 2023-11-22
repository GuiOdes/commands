package br.com.sapiencia.command.model

import br.com.sapiencia.command.database.entity.ItemComanda
import br.com.sapiencia.command.database.entity.Pagamento
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

data class ComandaModel(
    val id: UUID? = null,
    val nomeResponsavel: String,
    val numeroMesa: Long,
    var ativa: Boolean = true,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    var desconto: Double = 0.00,
    val listaItens: List<ItemComanda>?,
    val listaPagamento: List<Pagamento>?
)
