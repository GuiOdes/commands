package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.FuncionarioModel
import java.util.UUID

interface FuncionarioRepository {
    fun save(funcionarioModel: FuncionarioModel): FuncionarioModel
    fun deleteById(id: UUID)
}
