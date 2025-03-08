package com.mercadolivro.services


import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repositories.CustomerRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {


    @Transactional
    fun createCustomer(customer: CustomerModel): CustomerModel {
        return customerRepository.save(customer)
    }


    fun getById(id: UUID): CustomerModel? {
        val findById = customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR:Customer not found with provide Id in database")
        }
        return findById
    }
}