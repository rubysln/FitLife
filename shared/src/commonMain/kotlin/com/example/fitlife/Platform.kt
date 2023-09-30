package com.example.fitlife

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform