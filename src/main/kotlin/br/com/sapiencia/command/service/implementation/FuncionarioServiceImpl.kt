package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.database.repository.FuncionarioRepository
import br.com.sapiencia.command.service.FuncionarioService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FuncionarioServiceImpl(
    private val repository: FuncionarioRepository
) : FuncionarioService {
    override fun save(request: CriarFuncionarioRequest) = repository.salvar(request).toResponse()

    override fun deleteById(id: UUID) = repository.deletarPorId(id)
}
