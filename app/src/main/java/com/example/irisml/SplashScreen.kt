package com.example.irisml

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var startButton: Button
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startButton = findViewById(R.id.btnstart)

        startButton.setOnClickListener {
            val Intent = Intent(this,Menu::class.java)
            startActivity(Intent)
        }
    }
}