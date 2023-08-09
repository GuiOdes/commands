package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.Funcionario
import br.com.sapiencia.command.database.repository.FuncionarioRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.model.FuncionarioModel
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FuncionarioRepositoryImpl(
    private val funcionarioJpaRepository: FuncionarioJpaRepository
) : FuncionarioRepository {
    override fun save(
        funcionarioModel: FuncionarioModel
    ) = funcionarioJpaRepository.save(Funcionario.of(funcionarioModel)).toModel()

    override fun deleteById(id: UUID) = funcionarioJpaRepository.deleteById(id)
}
