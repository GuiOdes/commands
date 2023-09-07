package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.api.request.LoginRequest
import br.com.sapiencia.command.api.response.AuthenticationResponse
import br.com.sapiencia.command.configurations.security.JwtService
import br.com.sapiencia.command.database.entity.Funcionario
import br.com.sapiencia.command.database.repository.FuncionarioRepository
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.service.FuncionarioService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FuncionarioServiceImpl(
    private val repository: FuncionarioRepository,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder
) : FuncionarioService {
    override fun save(request: CriarFuncionarioRequest) = repository
        .salvar(request.copy(senha = passwordEncoder.encode(request.senha)))
        .toResponse()

    override fun deleteById(id: UUID) = repository.deletarPorId(id)
    override fun autenticar(loginRequest: LoginRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                loginRequest.usuario,
                loginRequest.senha
            )
        )

        return repository.procurarPeloDocumento(loginRequest.usuario)?.let {
            jwtService.generateToken(it)
        } ?: throw NaoEncontradoException(Funcionario::class)
    }
}
