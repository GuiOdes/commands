package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.model.FuncionarioModel
import java.util.UUID

interface FuncionarioRepository {
    fun save(request: CriarFuncionarioRequest): FuncionarioModel
    fun deleteById(id: UUID)
}
