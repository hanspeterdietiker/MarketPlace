package com.marketcar.controller

import com.marketcar.dto.auth.LogInRequest
import com.marketcar.dto.auth.LogInResponse
import com.marketcar.services.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    val authenticationService: AuthenticationService,
) {
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun auth(@Valid @RequestBody customerLogin: LogInRequest): LogInResponse =
        authenticationService.authentication(customerLogin)


}