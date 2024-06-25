package com.example.irisml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Dataset : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataset)

        val buttonOpenWebsite: Button = findViewById(R.id.btnds)
        buttonOpenWebsite.setOnClickListener {
            val webpage: Uri = Uri.parse("https://www.kaggle.com/datasets/rinichristy/2022-fuel-consumption-ratings")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
            //openWebsite("https://www.kaggle.com/datasets/rinichristy/2022-fuel-consumption-ratings")
        }
    }

    private fun openWebsite(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        // Cek apakah ada aplikasi yang bisa menangani intent ini
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}