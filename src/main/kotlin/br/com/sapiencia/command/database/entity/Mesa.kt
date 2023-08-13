package br.com.sapiencia.command.database.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "mesa")
data class Mesa(
    @Id
    @GeneratedValue
    val id: Long
)
