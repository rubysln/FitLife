package com.example.retrofit.api.authentication

import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {

    @POST("in/auth")
    suspend fun authorisation(@Body authorisationRequest: AuthorisationRequest): String

    @POST("in/register")
    suspend fun authorisation(@Body registrationRequest: RegistrationRequest): String
}