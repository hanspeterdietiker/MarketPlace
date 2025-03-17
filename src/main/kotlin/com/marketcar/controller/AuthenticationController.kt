package com.marketcar.controller

import com.marketcar.dto.auth.LogInRequest
import com.marketcar.dto.auth.LogInResponse
import com.marketcar.dto.customer.PostCustomerRequest
import com.marketcar.model.CustomerModel
import com.marketcar.model.enums.CustomerStatus
import com.marketcar.services.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(
    val authenticationService: AuthenticationService,

    ) {
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun signUp(@Valid @RequestBody customer: PostCustomerRequest) =
        authenticationService.signUp(
            customer = CustomerModel(
                name = customer.name,
                email = customer.email,
                password = customer.password,
                status = CustomerStatus.ACTIVE
            )

        )


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    fun login(@Valid @RequestBody customerLogin: LogInRequest): LogInResponse =
        authenticationService.authentication(customerLogin)

}