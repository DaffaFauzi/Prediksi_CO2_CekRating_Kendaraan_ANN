package com.example.irisml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView

class Model : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_model)

        val textViewOrderedList: TextView = findViewById(R.id.textView_ordered_list)
        val orderedListHtml = """
            <ol>
                <li>Pada lapisan pertama, kita menggunakan lapisan dense dengan 50 neuron dan fungsi aktivasi ReLU. ReLU sendiri menghasilkan nilai 0 jika input kurang dari 0, dan nilai input itu sendiri jika lebih besar dari 0</li>
                <li>Pada lapisan kedua, kita menggunakan lapisan dense dengan 100 neuron, juga dengan aktivasi ReLU yang sama</li>
                <li>Pada lapisan ketiga, kita menggunakan lapisan dense dengan jumlah neuron sebanyak jumlah data unik pada kolom co2 rating, yaitu 10, menggunakan aktivasi softmax. Fungsi aktivasi softmax mengubah output jaringan menjadi distribusi probabilitas, di mana total dari semua probabilitas adalah 1</li>
            </ol>
        """.trimIndent()
        textViewOrderedList.text = Html.fromHtml(orderedListHtml, Html.FROM_HTML_MODE_LEGACY)
    }
}