package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Funcionario
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FuncionarioJpaRepository : JpaRepository<Funcionario, UUID>
