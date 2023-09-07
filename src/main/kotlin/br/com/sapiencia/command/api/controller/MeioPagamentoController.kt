package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.MeioPagamentoRequest
import br.com.sapiencia.command.database.entity.MeioPagamento
import br.com.sapiencia.command.service.MeioPagamentoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
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
    fun salvar(@RequestBody meioPagamentoRequest: MeioPagamentoRequest) = meioPagamentoService.salvar(meioPagamentoRequest)
    @GetMapping
    fun listarTodos() = meioPagamentoService.listarTodos()

    @GetMapping
    fun listarPorMeioPagamento(@RequestBody meioPagamentoRequest: MeioPagamentoRequest) = meioPagamentoService.listarPorMeioPagamento(meioPagamentoRequest)
}