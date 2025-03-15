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
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

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
            .addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

            .csrf { it.disable() }

        return http.build()
    }

    @Bean
    fun userDetailsService(customerRepository: CustomerRepository): UserDetailsService {

    }

    @Bean
    fun authenticationProvider(customerRepository: CustomerRepository): DaoAuthenticationProvider =
        DaoAuthenticationProvider()
            .also { auth ->
                auth.setPasswordEncoder(encoder())
                auth.setUserDetailsService(userDetailsService)
            }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager =
        authConfig.authenticationManager
    }

