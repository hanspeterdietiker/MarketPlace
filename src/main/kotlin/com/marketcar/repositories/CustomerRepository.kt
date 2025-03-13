package com.marketcar.repositories

import com.marketcar.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CustomerRepository : CrudRepository<CustomerModel, UUID> {
    fun findByName(name: String): CustomerModel?
}