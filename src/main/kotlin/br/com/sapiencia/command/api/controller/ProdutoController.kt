package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.ProdutoRequest
import br.com.sapiencia.command.service.ProdutoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produto")
class ProdutoController(
    private val produtoService: ProdutoService
) {

    @GetMapping
    fun listarTodos() = produtoService.listarTodos()

    @PostMapping
    fun salvar(@RequestBody produtoRequest: ProdutoRequest) = produtoService.salvar(produtoRequest)
}
