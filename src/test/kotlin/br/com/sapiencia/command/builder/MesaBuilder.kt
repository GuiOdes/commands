package br.com.sapiencia.command.builder

import br.com.sapiencia.command.database.entity.Mesa

object MesaBuilder {

    fun mesaEntity(
        id: Long = 1
    ) = Mesa(
        id = id
    )
}
