package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Comanda
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.UUID

interface ComandaJpaRepository : JpaRepository<Comanda, UUID> {
    fun findByMesaIdAndAtivaIsTrue(mesaId: Long): Comanda?
    fun findAllByDataCriacaoBetween(dataInicial: LocalDateTime, dataFinal: LocalDateTime): List<Comanda>
}
