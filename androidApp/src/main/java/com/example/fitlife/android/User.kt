package com.example.fitlife.android

import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHRpZ3VuIiwiaWF0IjoxNjk2MTU0OTY0LCJleHAiOjE2OTY3NTk3NjR9.2NKxyf52rF9e0s_bhlSeGAt4Ig0hr2iInwxpkWqjmSA"
        val url = "http://localhost:8080/api/in/auth" // Замените на URL вашего API
        val json = JSONObject()
        json.put("username", "shtigun")
        json.put("password", "Shtigun1999M")

        // Создайте тело запроса с JSON-данными
        val requestBody =
            json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $jwtToken")
            .post(requestBody) // Добавляем JWT-токен в заголовок
            .build()

        CoroutineScope(Dispatchers.IO).launch{
            try {
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    return@launch
                } else {
                    println("Ошибка при выполнении запроса. Код ответа: ${response.code}")
                }
            } catch (e: Exception) {
                println("Ошибка при выполнении запроса: ${e.message}")
                return@launch
            }
        }

        /*try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                return true
            } else {
                println("Ошибка при выполнении запроса. Код ответа: ${response.code}")
            }
        } catch (e: Exception) {
            println("Ошибка при выполнении запроса: ${e.message}")
            return false
        }*/
        return false
    }
}