package com.marketcar.dto.car

import com.marketcar.model.enums.CarStatus
import java.math.BigDecimal

data class PutCarRequest(
    val price: BigDecimal,
    val name: String,
    val status: CarStatus,

)
