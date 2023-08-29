package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.Mesa
import br.com.sapiencia.command.database.repository.MesaRepository
import br.com.sapiencia.command.database.repository.data.MesaJpaRepository
import br.com.sapiencia.command.model.MesaModel
import org.springframework.stereotype.Component

@Component
class MesaRepositoryImpl(
    private val mesaJpaRepository: MesaJpaRepository
) : MesaRepository {
    override fun salvar(mesaModel: MesaModel) = mesaJpaRepository.save(Mesa.of(mesaModel)).toModel()

    override fun procurarPorId(id: Long) = mesaJpaRepository.findById(id).orElse(null)?.toModel()
    override fun deletarPorID(id: Long) = mesaJpaRepository.deleteById(id)
    override fun existeMesaAtivaPorId(id: Long): Boolean = mesaJpaRepository.existsByIdWhereStatusTrue(id)
}
