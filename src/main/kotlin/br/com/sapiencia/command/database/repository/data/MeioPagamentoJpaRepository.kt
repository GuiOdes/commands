package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.api.request.MeioPagamentoRequest
import br.com.sapiencia.command.database.entity.MeioPagamento
import br.com.sapiencia.command.model.MeioPagamentoModel
import org.springframework.data.jpa.repository.JpaRepository

interface MeioPagamentoJpaRepository: JpaRepository<MeioPagamento, Long> {

    fun findAllByName(meioPagamentoRequest: MeioPagamentoRequest): List<MeioPagamento?>
}