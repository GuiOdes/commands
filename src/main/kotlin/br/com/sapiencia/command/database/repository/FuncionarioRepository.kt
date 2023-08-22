package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.model.FuncionarioModel
import org.springframework.security.core.userdetails.UserDetails
import java.util.UUID

interface FuncionarioRepository {
    fun salvar(request: CriarFuncionarioRequest): FuncionarioModel
    fun deletarPorId(id: UUID)
    fun procurarPeloDocumento(cpf: String): UserDetails?
}
