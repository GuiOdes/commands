package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.model.ComandaModel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/compra")
class CompraController (
    val compraService:CompraService
){
    @PostMapping
    fun salvar(@RequestBody comandaModel: ComandaModel) = compraService.save(comandaModel)
}