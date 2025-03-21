package com.marketcar.controller

import com.marketcar.dto.car.PostCarRequest
import com.marketcar.dto.car.PutCarRequest
import com.marketcar.model.CarModel
import com.marketcar.services.CarService
import com.marketcar.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/v1/cars")
class CarController(
    val carService: CarService,
    val customerService: CustomerService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getCars(): List<CarModel> {
        return carService.getSaleCars()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdCar(@PathVariable id: UUID): ResponseEntity<CarModel> {
        val carById = carService.getByIdCar(id)
        return ResponseEntity.ok(carById)
    }

    @PostMapping("/add-sale")
    @ResponseStatus(HttpStatus.CREATED)
    fun createSaleCar(@RequestBody request: PostCarRequest) {
        val customer = customerService.getById(request.customerId)
        carService.addSaleCar(
            car = CarModel(
                name = request.name,
                price = request.price,
                status = request.status,
                customer = customer,
            )
        )
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateSaleCar(@PathVariable id: UUID, @RequestBody request: PutCarRequest) {
        carService.updateSaleCar(
            id = id,
            updateCarSale = request,

            )

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteSaleCar(@PathVariable id: UUID) {
        carService.deleteSaleCar(id)
    }

}