package br.com.sapiencia.command.service

import br.com.sapiencia.command.model.FuncionarioModel
import java.util.UUID

interface FuncionarioService {
    fun save(funcionarioModel: FuncionarioModel): FuncionarioModel
    fun deleteById(id: UUID)
}
