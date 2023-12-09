package br.com.sapiencia.command.model

import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.entity.Pagamento
import java.time.LocalDateTime
import java.util.UUID

data class CompraModel(
    val id: UUID? = null,
    val valorFinal: Double,
    val desconto: Double,
    val dataCompra: LocalDateTime = LocalDateTime.now(),
    val comanda: Comanda,
    val listaPagamento: MutableList<Pagamento>
)
