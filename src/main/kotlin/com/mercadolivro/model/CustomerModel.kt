package com.mercadolivro.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class CustomerModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long,

    var name: String,
    var password: String,
    var email: String

    )
