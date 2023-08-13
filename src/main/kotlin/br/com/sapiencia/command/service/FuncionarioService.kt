package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.FuncionarioResponse
import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import java.util.UUID

interface FuncionarioService {
    fun save(request: CriarFuncionarioRequest): FuncionarioResponse
    fun deleteById(id: UUID)
}
