package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Mesa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MesaJpaRepository : JpaRepository<Mesa, Long> {

    @Query("select count(*) = 1 from mesa where id = ? and status = true")
    fun existsByIdWhereStatusTrue(id: Long): Boolean
}
