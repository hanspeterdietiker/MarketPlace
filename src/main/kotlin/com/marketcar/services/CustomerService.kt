package com.marketcar.services


import com.marketcar.dto.customer.PutCustomerRequest
import com.marketcar.model.CustomerModel
import com.marketcar.model.enums.CustomerStatus
import com.marketcar.repositories.CarRepository
import com.marketcar.repositories.CustomerRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val passwordEncoder: PasswordEncoder,
    val carService: CarService,
) {


    fun getById(id: UUID): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Error getting customer with ID $id not found")
        }
    }

    @Transactional
    fun updateCustomer(id: UUID, updateCustomer: PutCustomerRequest): CustomerModel {
        return try {
            val existingCustomer = getById(id)
            existingCustomer.email = updateCustomer.email
            existingCustomer.password = passwordEncoder.encode(updateCustomer.password)

            customerRepository.save(existingCustomer)
        } catch (e: PersistenceException) {
            throw PersistenceException("ERROR: Error updating customer with ID $id not found")
        }

    }

    @Transactional
    fun deleteById(id: UUID) {
        val customer = getById(id)
        carService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INACTIVE
        customerRepository.save(customer)
    }

}