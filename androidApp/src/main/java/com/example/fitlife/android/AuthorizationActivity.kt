package com.example.fitlife.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fitlife.android.R.layout.activity_authorization)

        val linkToRegistration: TextView =findViewById(com.example.fitlife.android.R.id.link_to_registration)
        linkToRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        val buttonLogin: TextView =findViewById(com.example.fitlife.android.R.id.button_login)
        buttonLogin.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}