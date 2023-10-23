package com.example.retrofit.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    //private val BASE_URL = "https://fitlife-1hyp.onrender.com/api/"
    private val BASE_URL = "http://localhost:8080/api/"

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}