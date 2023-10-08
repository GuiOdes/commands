package br.com.sapiencia.command.api.controller

import br.com.sapiencia.command.api.request.AlterarDescontoRequest
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.PagamentoModel
import br.com.sapiencia.command.service.CompraService
import br.com.sapiencia.command.service.PagamentoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
@RestController
@RequestMapping("/compra")
class CompraController(
    val compraService: CompraService,
    val pagamentoService: PagamentoService
) {
    @PostMapping
    fun salvar(@RequestBody comandaModel: ComandaModel) = compraService.salvar(comandaModel)
    @GetMapping("/{compraId}")
    fun listarCompra(@PathVariable compraId: UUID) = compraService.listarCompra(compraId)

    @PostMapping("/pagamento")
    fun adiconarPagamento(@RequestBody pagamentoModel: PagamentoModel): PagamentoModel {
        return pagamentoService.adicionarPagamento(pagamentoModel)
    }
    @PutMapping("/desconto")
    fun editarDesconto(@RequestBody alterarDescontoRequest: AlterarDescontoRequest): ComandaModel {
        return pagamentoService.editarDesconto(alterarDescontoRequest)
    }
}
