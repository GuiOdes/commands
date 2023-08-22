package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.database.entity.Cargo
import br.com.sapiencia.command.database.entity.Funcionario
import br.com.sapiencia.command.database.repository.FuncionarioRepository
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.database.repository.data.FuncionarioJpaRepository
import br.com.sapiencia.command.exception.NotFoundException
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class FuncionarioRepositoryImpl(
    private val funcionarioJpaRepository: FuncionarioJpaRepository,
    private val cargoJpaRepository: CargoJpaRepository
) : FuncionarioRepository {
    override fun salvar(
        request: CriarFuncionarioRequest
    ) = cargoJpaRepository.findByNome(request.cargoName)?.let {
        funcionarioJpaRepository.save(Funcionario.of(request, it))
    }?.toModel() ?: throw NotFoundException(Cargo::class)

    override fun deletarPorId(id: UUID) = funcionarioJpaRepository.deleteById(id)
    override fun procurarPeloDocumento(cpf: String) = funcionarioJpaRepository.findByCpf(cpf)
}
