package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Cargo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CargoJpaRepository : JpaRepository<Cargo, UUID> {
    fun findByNome(nome: String): Cargo?
}
