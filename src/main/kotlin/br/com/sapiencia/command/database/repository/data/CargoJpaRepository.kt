package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Cargo
import org.springframework.data.jpa.repository.JpaRepository

interface CargoJpaRepository : JpaRepository<Cargo, Long>
