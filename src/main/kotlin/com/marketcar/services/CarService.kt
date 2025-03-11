package com.marketcar.services

import com.marketcar.dto.car.PutCarRequest
import com.marketcar.model.CarModel
import com.marketcar.repositories.CarRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class CarService(
    val carRepository: CarRepository,
) {
    @Transactional
    fun addSaleCar(car: CarModel): CarModel {
        try {
            return carRepository.save(car)
        } catch (e: PersistenceException) {
            throw PersistenceException("ERROR: Error registering car sale in the database \n ${e.message}", e)
        }
    }

    fun getSaleCars(): List<CarModel> {
        return carRepository.findAll()
    }

    fun getByIdCar(id: UUID): CarModel? {
        return carRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Error getting Car sale with ID $id not found")
        }
    }

    @Transactional
    fun updateSaleCar(id: UUID, updateCarSale: PutCarRequest): CarModel {
        return try {
            val existingSaleCar = getByIdCar(id)
            existingSaleCar!!.price = updateCarSale.price
            existingSaleCar.name = updateCarSale.name
            existingSaleCar.status = updateCarSale.status

            carRepository.save(existingSaleCar)
        } catch (e: PersistenceException) {
            throw PersistenceException("ERROR: Error updating car sale in the database \n ${e.message}", e)
        }
    }
}