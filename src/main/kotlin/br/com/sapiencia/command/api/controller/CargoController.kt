package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.AlterarCargoRequest
import br.com.sapiencia.command.api.request.CargoRequest
import br.com.sapiencia.command.service.CargoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cargo")
class CargoController(
    val cargoService: CargoService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@RequestBody cargoRequest: CargoRequest) = cargoService.salvar(cargoRequest)

    @GetMapping
    fun listarTodos() = cargoService.listarTodos()

    @PutMapping
    fun editar(@RequestBody alterarCargoRequest: AlterarCargoRequest) = cargoService.editar(alterarCargoRequest)
}
