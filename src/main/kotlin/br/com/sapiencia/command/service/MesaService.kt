package br.com.sapiencia.command.service

import br.com.sapiencia.command.api.response.MesaResponse
import br.com.sapiencia.command.model.MesaModel

interface MesaService {
    fun salvar(mesaModel: MesaModel): MesaModel
    fun procurarPorId(id: Long): MesaResponse
    fun deletarPorId(id: Long)
}
