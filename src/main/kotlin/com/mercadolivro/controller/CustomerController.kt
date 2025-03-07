package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/v1/customers")
class MercadolivroController(
    val customerService: CustomerService
) {

    @GetMapping("/")
    fun getAllCustomer(@RequestParam name:String?) {

    }

    @GetMapping("/{id}")
    fun getByIdCustomer(@PathVariable id: Long) {

    }

    @PostMapping("/sign")
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Long, @RequestBody customer: PutCustomerRequest) {

    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {

    }
}