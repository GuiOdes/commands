package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.service.CompraService
import br.com.sapiencia.command.service.PagamentoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/compra")
class CompraController (
    val compraService: CompraService,
    val pagamentoService: PagamentoService
){
    @PostMapping
    fun salvar(@RequestBody comandaModel: ComandaModel) = compraService.salvar(comandaModel)


}