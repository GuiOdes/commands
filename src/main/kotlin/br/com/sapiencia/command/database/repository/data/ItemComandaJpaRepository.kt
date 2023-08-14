package br.com.sapiencia.command.database.repository.data

import br.com.sapiencia.command.database.entity.ItemComanda
import br.com.sapiencia.command.database.entity.ItemComandaKey
import org.springframework.data.jpa.repository.JpaRepository

interface ItemComandaJpaRepository : JpaRepository<ItemComanda, ItemComandaKey>
