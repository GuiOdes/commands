package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.FuncionarioResponse
import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.api.response.AuthenticationResponse
import java.util.UUID

interface FuncionarioService {
    fun save(request: CriarFuncionarioRequest): FuncionarioResponse
    fun deleteById(id: UUID)
    fun autenticar(loginRequest: CriarFuncionarioRequest.LoginRequest): AuthenticationResponse
}
