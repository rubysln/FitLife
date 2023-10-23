package com.example.retrofit.api.authentication

data class RegistrationRequest(
    val username: String,
    val email: String,
    val password: String
)
