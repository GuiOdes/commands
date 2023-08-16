package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.response.MesaResponse
import br.com.sapiencia.command.model.MesaModel
import br.com.sapiencia.command.service.MesaService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mesa")
class MesaController(
    val mesaService: MesaService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun salvar(@RequestBody mesaModel: MesaModel) = mesaService.salvar(mesaModel)
    @GetMapping("/{id}")
    fun procurarPorId(@PathVariable id: Long): MesaResponse {
        return mesaService.procurarPorId(id)
    }
}
