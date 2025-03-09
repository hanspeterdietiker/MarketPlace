package com.mercadolivro.services


import com.mercadolivro.dto.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repositories.CustomerRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import java.util.*

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {


    @Transactional
    fun createCustomer(customer: CustomerModel): CustomerModel {
        return try {
            customerRepository.save(customer)
        } catch (e: PersistenceException) {
            throw PersistenceException("ERROR: Error registering Customer in the database \n ${e.message}", e)
        }
    }


    fun getById(id: UUID): CustomerModel? {
        val findById = customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Error getting customer with ID $id not found")
        }
        return findById
    }

    fun updateCustomer(@PathVariable id: UUID, updateCustomer: PutCustomerRequest): CustomerModel {
        val existingCustomer = customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Error getting customer with ID $id not found ")
        }
        existingCustomer.email = updateCustomer.email
        existingCustomer.password = updateCustomer.password

        return customerRepository.save(existingCustomer)
    }


    fun deleteById(id: UUID) {
        val deleteCustomer = customerRepository.findById(id).orElseThrow {
            EntityNotFoundException("ERROR: Customer with ID $id to delete not found")
        }
        return customerRepository.delete(deleteCustomer)
    }

}