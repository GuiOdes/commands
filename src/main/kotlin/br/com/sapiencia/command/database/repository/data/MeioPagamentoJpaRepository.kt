package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.MeioPagamento
import org.springframework.data.jpa.repository.JpaRepository

interface MeioPagamentoJpaRepository : JpaRepository<MeioPagamento, Long> {

    fun findByNome(nome: String): MeioPagamento?
}
