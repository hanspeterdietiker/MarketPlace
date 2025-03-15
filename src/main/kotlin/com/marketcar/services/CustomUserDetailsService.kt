package com.marketcar.services

import com.marketcar.model.CustomerModel
import com.marketcar.repositories.CustomerRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

typealias ApplicationUser = com.marketcar.model.CustomerModel

@Service
class CustomUserDetailsService(
    private val customerRepository: CustomerRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails =
        customerRepository.findByName(username)
            ?.mapToUserDetails()
            ?: throw EntityNotFoundException("Customer $username not found")

    private fun CustomerModel.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.name)
            .password(this.password)
            .build()


}