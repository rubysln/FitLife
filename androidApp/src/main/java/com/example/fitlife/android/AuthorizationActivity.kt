package com.example.fitlife.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AuthorizationActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        val linkToRegistration: TextView =
            findViewById(R.id.link_to_registration)
        linkToRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        val buttonLogin: TextView = findViewById(R.id.button_login)
        buttonLogin.setOnClickListener {
            /*val client = OkHttpClient()
            val jwtToken =
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaHRpZ3VuIiwiaWF0IjoxNjk2MTYzNTg5LCJleHAiOjE2OTY3NjgzODl9.dp-1_1dix9_fSHbGj1zzfFSjJIreotCOOLQyfA6xCJQ"
            val url = "http://localhost:8080/api/in/auth" // Замените на URL вашего API
            val json = JSONObject()
            json.put("username", "shtigun")
            json.put("password", "Shtigun1999M")

            // Создайте тело запроса с JSON-данными
            val requestBody =
                RequestBody.create("application/json".toMediaTypeOrNull(), json.toString())
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Authorization", "Bearer $jwtToken") // Добавляем JWT-токен в заголовок
                .build()

            try {
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                } else {
                    println("Ошибка при выполнении запроса. Код ответа: ${response.code}")
                }
            } catch (e: Exception) {
                println("Ошибка при выполнении запроса: ${e.message}")
            }*/
            if(User().getData()){
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

    /*fun getUsers() {
        val url = "http://localhost:8080/api/in/auth" // Замените на URL вашего API
        val json = JSONObject()
        json.put("username", "shtigun")
        json.put("password", "Shtigun1999M")

        val requestBody = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .header("Authorization", "Bearer $jwtToken")
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                val responseData = response.body?.string()

                // Десериализация JSON в объект Kotlin с использованием Gson
                val gson = Gson()
                val tokenResponse = gson.fromJson(responseData, TokenResponse::class.java)

                // Теперь у вас есть доступ к полю `token`
                println("Token: ${tokenResponse.token}")
            } else {
                println("Ошибка при выполнении запроса. Код ответа: ${response.code}")
            }
        } catch (e: Exception) {
            println("Ошибка при выполнении запроса: ${e.message}")
        }

    }*/



