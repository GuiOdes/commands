package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.Pagamento
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface PagamentoJpaRepository : JpaRepository<Pagamento, UUID>
