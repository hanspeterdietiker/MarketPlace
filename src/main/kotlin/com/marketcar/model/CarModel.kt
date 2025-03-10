package com.marketcar.model

import com.marketcar.model.enums.CarStatus
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.*

@Entity
data class CarModel(
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(nullable = false)
    var id: UUID? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var price: BigDecimal,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: CarStatus? = null,

    @JoinColumn(name = "customer_id")
    @ManyToOne
    var customer: CustomerModel? = null,
) {

    constructor() : this(UUID.randomUUID(), "", BigDecimal.ZERO, null, null)
}
