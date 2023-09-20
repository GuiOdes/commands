package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.database.repository.PagamentoRepository
import br.com.sapiencia.command.service.PagamentoService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PagamentoServiceImpl(
    private val pagamentoRepository: PagamentoRepository,
):PagamentoService {
}