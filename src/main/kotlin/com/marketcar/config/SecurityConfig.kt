package com.marketcar.config

import com.marketcar.repositories.CustomerRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(
                    HttpMethod.POST, "/api/v1/customers/sign",
                    "/api/v1/auth/login"
                ).permitAll()
                auth.anyRequest().authenticated()

            }

            .csrf { it.disable() }

        return http.build()
    }

    @Bean
    fun authManager(userDetailsService: UserDetailsService, passwordEncoder: PasswordEncoder): AuthenticationManager {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userDetailsService)
        authProvider.setPasswordEncoder(passwordEncoder)
        return ProviderManager(authProvider)
    }

    @Bean
    fun userDetailsService(customerRepository: CustomerRepository): UserDetailsService {
        return try {
            UserDetailsService { name ->
                val customer = customerRepository.findByName(name)

                User
                    .withUsername(customer!!.name)
                    .password(customer.password)
                    .build()

            }
        } catch (e: EntityNotFoundException) {
            throw EntityNotFoundException("Customer not found : ${e.message}")
        }

    }


}