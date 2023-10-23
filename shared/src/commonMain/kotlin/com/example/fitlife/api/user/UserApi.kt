package com.example.retrofit.api.user

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface UserApi {

    @Headers(
        "Content-Type: application/json"
    )
    @GET("users")
    suspend fun getAllUsers(@Header("Authorization") token: String): List<User>

    @Headers(
        "Content-Type: application/json"
    )
    @GET("users/{id}")
    suspend fun getUserById(@Header("Authorization") token: String, @Path("id") id: Int): User

    @Headers(
        "Content-Type: application/json"
    )
    @DELETE("users/{id}")
    suspend fun deleteUserById(@Header("Authorization") token: String, @Path("id") id: Int): List<User>
}