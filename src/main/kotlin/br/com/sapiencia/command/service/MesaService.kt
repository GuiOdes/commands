package br.com.sapiencia.command.service

import br.com.sapiencia.command.model.MesaModel

interface MesaService {
    fun salvar(mesaModel: MesaModel): MesaModel
    fun procurarPorId(id: Long): MesaModel?
}
