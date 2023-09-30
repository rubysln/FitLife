package com.example.fitlife.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.fitlife.R

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fitlife.android.R.layout.activity_registration)

        val linkToAuthorisation: TextView=findViewById(com.example.fitlife.android.R.id.link_to_authorisation)
        linkToAuthorisation.setOnClickListener {
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
        }
    }
}