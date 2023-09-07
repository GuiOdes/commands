package br.com.sapiencia.command.database.repository

import br.com.sapiencia.command.model.MesaModel

interface MesaRepository {
    fun salvar(mesaModel: MesaModel): MesaModel
    fun procurarPorId(id: Long): MesaModel?
    fun deletarPorID(id: Long)
    fun existeMesaAtivaPorId(id: Long): Boolean
}
