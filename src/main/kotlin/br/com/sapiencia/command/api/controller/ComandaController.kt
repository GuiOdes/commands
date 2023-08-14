package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.ComandaRequest
import br.com.sapiencia.command.api.request.InserirProdutoRequest
import br.com.sapiencia.command.service.ComandaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/comanda")
class ComandaController(
    private val comandaService: ComandaService
) {

    @PostMapping
    fun salvar(@RequestBody comandaRequest: ComandaRequest) = comandaService.salvar(comandaRequest)

    @PostMapping("/inserir-produto")
    fun inserirProduto(
        @RequestBody inserirProdutoRequest: InserirProdutoRequest
    ) = comandaService.inserirProduto(inserirProdutoRequest)

    @GetMapping
    fun procurarAtivaPorMesa(mesa: Long) = comandaService.procurarAtivaPorMesa(mesa)

    @GetMapping("/periodo")
    fun procurarPorPeriodo(
        dataInicial: LocalDateTime,
        dataFinal: LocalDateTime
    ) = comandaService.procurarPorPeriodo(dataInicial, dataFinal)
}
