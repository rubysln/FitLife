package com.example.fitlife.android

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class MenuActivity : AppCompatActivity() {

    private var dataValue: Int = 0
    @SuppressLint("SuspiciousIndentation", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.fitlife.android.R.layout.activity_menu)

        val progressBar: ProgressBar = findViewById(R.id.progressBarKM)
        dataValue = 60 // Установите новое значение данных
        updateProgressBar(progressBar)

        val Navigation: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        Navigation.setOnItemSelectedListener() { menuItem ->
            when(menuItem.itemId){
                R.id.home -> {
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    true // Возвращаем true, чтобы отметить, что событие было обработано
                }
                R.id.journal -> {
                    val intent = Intent(this, JournalActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)

                    true
                }
                else -> false
            }
        }

    }
    private fun updateProgressBar(progressBar: ProgressBar) {
        // Получите максимальное значение ProgressBar (например, 100)
        val maxValue = 100

        // Вычислите процент заполнения ProgressBar
        val progress = (dataValue.toFloat() / maxValue * 100).toInt()

        // Установите значение ProgressBar и сделайте его видимым
        progressBar.progress = progress
        progressBar.visibility = ProgressBar.VISIBLE
    }
}