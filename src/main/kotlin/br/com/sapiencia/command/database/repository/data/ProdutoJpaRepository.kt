package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Produto
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProdutoJpaRepository : JpaRepository<Produto, UUID>
