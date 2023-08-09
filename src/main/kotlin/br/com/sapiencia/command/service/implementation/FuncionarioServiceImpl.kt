package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.database.repository.FuncionarioRepository
import br.com.sapiencia.command.model.FuncionarioModel
import br.com.sapiencia.command.service.FuncionarioService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FuncionarioServiceImpl(
    private val repository: FuncionarioRepository
) : FuncionarioService {
    override fun save(funcionarioModel: FuncionarioModel) = repository.save(funcionarioModel)

    override fun deleteById(id: UUID) = repository.deleteById(id)
}
