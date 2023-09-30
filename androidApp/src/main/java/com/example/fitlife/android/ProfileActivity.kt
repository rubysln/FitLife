package com.example.fitlife.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class ProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fitlife.android.R.layout.activity_profile)

        val Navigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)


        Navigation.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home -> {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    menuItem.isEnabled = true
                    true // Возвращаем true, чтобы отметить, что событие было обработано
                }
                R.id.journal -> {
                    val intent = Intent(this, JournalActivity::class.java)
                    startActivity(intent)
                    menuItem.isChecked = true
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    menuItem.isChecked = true
                    true
                }
                else -> false
            }
        }
    }
}