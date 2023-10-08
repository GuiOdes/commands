package br.com.sapiencia.command.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "compra")
data class Compra(
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    val id: UUID? = null,
    val valorFinal: Double,
    val desconto: Double,
    @Column(name = "data_criacao")
    @CreationTimestamp
    val dataCompra: LocalDateTime = LocalDateTime.now(),
    @OneToOne
    @JoinColumn(name = "comanda_id")
    val comanda: Comanda,

)
