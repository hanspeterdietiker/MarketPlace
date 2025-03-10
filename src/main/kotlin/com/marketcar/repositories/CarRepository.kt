package com.marketcar.repositories

import com.marketcar.model.CarModel
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CarRepository : JpaRepository<CarModel, UUID> {
}