package br.com.sapiencia.command.service.implementation

import br.com.sapiencia.command.api.request.AlterarDescontoRequest
import br.com.sapiencia.command.api.request.PagarRequest
import br.com.sapiencia.command.database.entity.Comanda
import br.com.sapiencia.command.database.entity.MeioPagamento
import br.com.sapiencia.command.database.repository.ComandaRepository
import br.com.sapiencia.command.database.repository.PagamentoRepository
import br.com.sapiencia.command.exception.NaoEncontradoException
import br.com.sapiencia.command.model.ComandaModel
import br.com.sapiencia.command.model.CompraModel
import br.com.sapiencia.command.model.PagamentoModel
import br.com.sapiencia.command.service.ComandaService
import br.com.sapiencia.command.service.CompraService
import br.com.sapiencia.command.service.MeioPagamentoService
import br.com.sapiencia.command.service.PagamentoService
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.UUID

@Service
class CompraServiceImpl(
    val pagamentoService: PagamentoService,
    val comandaService: ComandaService,
    val meioPagamentoService: MeioPagamentoService
):CompraService{
    override fun salvar(pagarRequest: PagarRequest): ComandaModel {
        val comandaModel = comandaService.procurarComandaPorId(pagarRequest.comandaId)
        val valorTotal = comandaModel!!.listaItens?.sumOf { it.valorTotal }?: BigDecimal.ZERO
        val valorComDesconto =
            if (valorTotal> BigDecimal.ZERO){
                valorTotal - valorTotal*comandaModel.desconto.toBigDecimal()
            } else{
                BigDecimal.ZERO
            }
        var valorPago = comandaModel!!.listaPagamento?.sumOf { it.valorPago }?:BigDecimal.ZERO
        valorPago += pagarRequest.valorPago
        if (valorComDesconto > BigDecimal.ZERO && valorPago <= valorComDesconto){
            val pagamentoModel = PagamentoModel(
                valorPago = pagarRequest.valorPago,
                meioPagamento = meioPagamentoService.procurarPorId(
                    pagarRequest.meioPagamentoId
                ) ?: throw NaoEncontradoException(MeioPagamento::class),
                comanda = comandaModel?:throw NaoEncontradoException(Comanda::class)
            )
            pagamentoService.adicionarPagamento(pagamentoModel)
        }
        if (valorPago == valorComDesconto){
            comandaModel.ativa = false
            comandaService.editarComanda(comandaModel)
        }
        return comandaModel
    }



}
