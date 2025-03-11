package com.marketcar.model

import jakarta.persistence.*
import java.util.UUID


@Entity
data class CustomerModel(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false)
    var email: String,


) {
    constructor() : this(UUID.randomUUID(), "", "", "")
}

