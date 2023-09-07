package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Mesa
import org.springframework.data.jpa.repository.JpaRepository

interface MesaJpaRepository : JpaRepository<Mesa, Long> {
    fun existsByIdAndStatusTrue(id: Long): Boolean
}
