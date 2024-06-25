package com.example.irisml

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }



    fun abouts(view: View?) {
        val Intent = Intent(this,about::class.java)
        startActivity(Intent)
    }

    fun dataset(view: View?) {
        val Intent = Intent(this,Dataset::class.java)
        startActivity(Intent)
    }

    fun fitur(view: View?) {
        val Intent = Intent(this,Fitur::class.java)
        startActivity(Intent)
    }

    fun model(view: View?) {
        val Intent = Intent(this,Model::class.java)
        startActivity(Intent)
    }

    fun main(view: View?) {
        val Intent = Intent(this,MainActivity::class.java)
        startActivity(Intent)
    }
}