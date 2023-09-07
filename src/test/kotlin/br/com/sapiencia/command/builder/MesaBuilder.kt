package br.com.sapiencia.command.builder

import br.com.sapiencia.command.database.entity.Mesa

object MesaBuilder {

    fun mesaEntity(
        id: Long = 1,
        status: Boolean = true
    ) = Mesa(
        id = id,
        status = status
    )
}
