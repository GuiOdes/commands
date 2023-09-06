package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.service.FuncionarioService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val funcionarioService: FuncionarioService
) {

    @PostMapping
    fun autenticar(loginRequest: CriarFuncionarioRequest.LoginRequest) = funcionarioService
        .autenticar(loginRequest)
}
