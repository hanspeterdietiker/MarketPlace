package com.marketcar.dto.car


import com.marketcar.model.enums.CarStatus
import java.math.BigDecimal
import java.util.UUID

data class PostCarRequest(
    var name: String,
    var price: BigDecimal,
    var status: CarStatus,
    var customerId: UUID
)