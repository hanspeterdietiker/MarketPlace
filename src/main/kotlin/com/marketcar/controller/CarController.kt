package com.marketcar.controller

import com.marketcar.services.CarService
import com.marketcar.services.CustomerService
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/cars")
class CarController(
    val carService: CarService,
    val customerService: CustomerService,
) {

//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    fun getByIdCar(@PathVariable id: UUID): ResponseEntity<CarModel> {
//        val carById = carService.getByIdCar(id)
//        return ResponseEntity.ok(carById)
//    }
//
//    @PostMapping("/sale")
//    @ResponseStatus(HttpStatus.CREATED)
//    fun sale(@RequestBody car: PostCarRequest): ResponseEntity<CarModel> {
//        val customer = customerService.getById(car.customerId)
//        val carSale = carService.addSaleCar(
//            car = CarModel(
//                name = car.name,
//                price = car.price,
//                status = car.status,
//
//            )
//        )
//        return ResponseEntity.status(HttpStatus.CREATED).body(carSale)
//    }
}