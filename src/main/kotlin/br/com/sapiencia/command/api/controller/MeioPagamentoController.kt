package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.service.MeioPagamentoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/meio_pagamento")
class MeioPagamentoController(
    val meioPagamentoService: MeioPagamentoService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun salvar(
        @RequestBody nomeMeioPagamento: String
    ) = meioPagamentoService.salvar(nomeMeioPagamento)
    @GetMapping
    fun listarTodos() = meioPagamentoService.listarTodos()

    @GetMapping("/{id}")
    fun listarPorMeioPagamento(
        @PathVariable id: Long
    ) = meioPagamentoService.procurarPorId(id)
}
