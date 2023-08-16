package br.com.sapiencia.command.database.repository.implementation

import br.com.sapiencia.command.database.entity.Cargo
import br.com.sapiencia.command.database.repository.CargoRepository
import br.com.sapiencia.command.database.repository.data.CargoJpaRepository
import br.com.sapiencia.command.model.CargoModel
import org.springframework.stereotype.Component

@Component
class CargoRepositoryImpl(private val cargoJpaRepository: CargoJpaRepository) : CargoRepository {
    override fun salvar(cargoModel: CargoModel) = cargoJpaRepository.save(Cargo.of(cargoModel)).toModel()
    override fun listarTodos() = cargoJpaRepository.findAll().map { it.toModel() }
}
