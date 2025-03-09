package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID


@RestController
@RequestMapping("api/v1/customers")
class MercadolivroController(
    val customerService: CustomerService
) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getByIdCustomer(@PathVariable id: UUID): ResponseEntity<CustomerModel> {
        val customerById = customerService.getById(id)
        return ResponseEntity.ok(customerById)
    }

    @PostMapping("/sign")
    @ResponseStatus(HttpStatus.CREATED)
    fun postCustomer(@RequestBody customer: PostCustomerRequest) {
        customerService.createCustomer(
            customer = CustomerModel(
                name = customer.name,
                email = customer.email,
                password = customer.password
            )

        )

    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun putCustomer(@PathVariable id: UUID, @RequestBody customer: PutCustomerRequest) {

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: UUID) {
        customerService.deleteById(id)

    }
}