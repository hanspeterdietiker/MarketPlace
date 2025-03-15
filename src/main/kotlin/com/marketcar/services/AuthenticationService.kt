package com.marketcar.services

import com.marketcar.config.JwtProperties
import com.marketcar.dto.auth.LogInRequest
import com.marketcar.dto.auth.LogInResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService(
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
}