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

    @OneToMany(mappedBy = "customer_cars", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @Column(nullable = false)
    var cars: MutableList<CarModel>?


) {
    constructor(name: String, email: String, password: String) : this(UUID.randomUUID(), "", "", "", mutableListOf())
}

