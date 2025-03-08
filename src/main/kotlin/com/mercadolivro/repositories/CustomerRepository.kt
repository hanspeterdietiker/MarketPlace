package com.mercadolivro.repositories

import com.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface CustomerRepository : CrudRepository<CustomerModel, UUID> {
}