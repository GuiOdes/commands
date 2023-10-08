package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.repository.PagamentoRepository
import br.com.sapiencia.command.database.repository.data.PagamentoJpaRepository
import org.springframework.stereotype.Component

@Component
class PagamentoRepositoryImpl(
    val pagamentoJpaRepository: PagamentoJpaRepository
) : PagamentoRepository
