package com.marketcar.controller

import com.marketcar.dto.auth.LogInRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val authenticationManager: AuthenticationManager,
) {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun auth(@Valid @RequestBody customerLogin: LogInRequest): ResponseEntity<Any> {
        val authRequest = UsernamePasswordAuthenticationToken(customerLogin.name, customerLogin.password)
        authenticationManager.authenticate(authRequest)
        return ResponseEntity.ok().build()
    }
}