package br.com.sapiencia.command.database.entity

import jakarta.persistence.Embeddable
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.io.Serializable

@Suppress("SerialVersionUIDInSerializableClass")
@Embeddable
class ItemComandaKey(
    @ManyToOne
    @JoinColumn(name = "comanda_id")
    var comanda: Comanda,

    @ManyToOne
    @JoinColumn(name = "produto_id")
    var produto: Produto
) : Serializable
