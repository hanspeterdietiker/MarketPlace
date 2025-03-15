package com.marketcar.model

import jakarta.persistence.*
import java.util.UUID


@Entity
data class CustomerModel(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    var id: UUID? = null,

    @Column(nullable = false, unique = true, length = 50)
    var name: String,

    @Column(nullable = false, length = 100)
    var password: String,

    @Column(nullable = false, unique = true, length = 100)
    var email: String,


    ) {
    constructor() : this(UUID.randomUUID(), "", "", "")
}

