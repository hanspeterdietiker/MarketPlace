package com.marketcar.services

import com.marketcar.config.JwtProperties
import com.marketcar.dto.auth.LogInRequest
import com.marketcar.dto.auth.LogInResponse
import com.marketcar.model.CustomerModel
import com.marketcar.repositories.CustomerRepository
import jakarta.persistence.PersistenceException
import jakarta.transaction.Transactional
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
    val customerRepository: CustomerRepository,
    val passwordEncoder: PasswordEncoder,
    val authenticationManager: AuthenticationManager,
    val userDetailsService: CustomUserDetailsService,
    val tokenService: TokenService,
    val jwtProperties: JwtProperties,
) {
    fun authentication(authRequest: LogInRequest): LogInResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.name,
                authRequest.password

            )
        )
        val customer = userDetailsService.loadUserByUsername(authRequest.name)
        val accessToken = tokenService.generate(
            userDetails = customer,
            expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration),
        )
        return LogInResponse(
            accessToken = accessToken,
        )
    }
    @Transactional
    fun signUp(customer: CustomerModel): CustomerModel {
        return try {
            customer.password = passwordEncoder.encode(customer.password)
            customerRepository.save(customer)
        } catch (e: PersistenceException) {
            throw PersistenceException("ERROR: Error registering Customer in the database \n ${e.message}", e)
        }
    }
}