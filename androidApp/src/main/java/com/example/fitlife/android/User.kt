package com.example.fitlife.android

import android.content.Intent
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject

class User {
    fun getData():Boolean {
        val client = OkHttpClient()
        val jwtToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHRpZ3VuIiwiaWF0IjoxNjk2MTYzNTg5LCJleHAiOjE2OTY3NjgzODl9.dp-1_1dix9_fSHbGj1zzfFSjJIreotCOOLQyfA6xCJQ"
        val url = "http://localhost:8080/api/in/auth" // Замените на URL вашего API
        val json = JSONObject()
        json.put("username", "shtigun")
        json.put("password", "Shtigun1999M")

        // Создайте тело запроса с JSON-данными
        val requestBody =
            json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .header("Authorization", "Bearer $jwtToken") // Добавляем JWT-токен в заголовок
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                return true
            } else {
                println("Ошибка при выполнении запроса. Код ответа: ${response.code}")
            }
        } catch (e: Exception) {
            println("Ошибка при выполнении запроса: ${e.message}")
            return false
        }
        return false
    }
}