package com.mercadolivro.controller.request



data class PostCustomerRequest(
    var name: String,
    var password: String,
    var email: String
)

