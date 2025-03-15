package com.marketcar.config

import com.marketcar.repositories.CustomerRepository
import com.marketcar.services.CustomUserDetailsService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Config {
    @Bean
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(customerRepository: CustomerRepository): UserDetailsService =
        CustomUserDetailsService(customerRepository)


    @Bean
    fun authenticationProvider(customerRepository: CustomerRepository): AuthenticationProvider =
        DaoAuthenticationProvider()
            .also { auth ->
                auth.setUserDetailsService(userDetailsService(customerRepository))
                auth.setPasswordEncoder(encoder())
            }

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager =
        authConfig.authenticationManager
}
