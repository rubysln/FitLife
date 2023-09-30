package com.example.fitlife.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fitlife.R
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class JournalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fitlife.android.R.layout.activity_journal)

        val Navigation: BottomNavigationView = findViewById(com.example.fitlife.android.R.id.bottomNavigationView)
        Navigation.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                com.example.fitlife.android.R.id.home -> {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    menuItem.isChecked = true
                    true // Возвращаем true, чтобы отметить, что событие было обработано
                }
                com.example.fitlife.android.R.id.journal -> {
                    val intent = Intent(this, JournalActivity::class.java)
                    startActivity(intent)
                    menuItem.isChecked = true
                    true
                }
                com.example.fitlife.android.R.id.profile -> {
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