package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.CriarFuncionarioRequest
import br.com.sapiencia.command.service.FuncionarioService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/funcionario")
class FuncionarioController(
    private val funcionarioService: FuncionarioService
) {

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    fun salvar(@RequestBody request: CriarFuncionarioRequest) = funcionarioService.save(request)

    @DeleteMapping("/deletar/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletarPorId(@PathVariable id: UUID) = funcionarioService.deleteById(id)
}
