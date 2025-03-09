package com.marketcar.services


import com.marketcar.dto.PutCustomerRequest
import com.marketcar.model.CustomerModel
import com.marketcar.repositories.CustomerRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val passwordEncoder: PasswordEncoder
) {


    @Transactional
    fun createCustomer(customer: CustomerModel): CustomerModel {
        return try {
            customer.password = passwordEncoder.encode(customer.password)
            customerRepository.save(customer)
        } catch (e: PersistenceException) {
            throw PersistenceException("ERROR: Error registering Customer in the database \n ${e.message}", e)
        }
    }


    fun getById(id: UUID): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Error getting customer with ID $id not found")
        }
    }

    @Transactional
    fun updateCustomer(id: UUID, updateCustomer: PutCustomerRequest): CustomerModel {
        val existingCustomer = getById(id)
        existingCustomer.email = updateCustomer.email
        existingCustomer.password = passwordEncoder.encode(updateCustomer.password)

        return customerRepository.save(existingCustomer)
    }

    @Transactional
    fun deleteById(id: UUID) {
        val deleteCustomer = customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Customer with ID $id to delete not found")
        }
        return customerRepository.delete(deleteCustomer)
    }

}