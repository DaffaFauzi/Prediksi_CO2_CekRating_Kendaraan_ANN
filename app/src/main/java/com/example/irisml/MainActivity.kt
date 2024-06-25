package com.example.irisml

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


private fun TensorBuffer.loadBuffer(inputVal: FloatArray) {

}

class MainActivity : AppCompatActivity() {
    private val mModelPath = "model_co2rating.tflite"

    private lateinit var resultText: TextView
    private lateinit var et1: EditText
    private lateinit var et2: EditText
    private lateinit var et3: EditText
    private lateinit var et4: EditText
    private lateinit var et5: EditText
    private lateinit var et6: EditText
    private lateinit var et7: EditText

    private lateinit var checkButton: Button
    private lateinit var tflite: Interpreter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = Interpreter.Options()
        tflite = Interpreter(loadModelFile(), options)

        resultText = findViewById(R.id.txtResult)
        et1 = findViewById(R.id.et1)
        et2 = findViewById(R.id.et2)
        et2.setFilters(arrayOf<InputFilter>(InputFilterMinMax("1", "10")))
        et3 = findViewById(R.id.et3)
        et4 = findViewById(R.id.et4)
        et5 = findViewById(R.id.et5)
        et6 = findViewById(R.id.et6)
        et7 = findViewById(R.id.et7)
        checkButton = findViewById(R.id.btnCheck)
        checkButton.setOnClickListener {
            val inputArray = FloatArray(7)
            inputArray[0] = et1.text.toString().toFloat()
            inputArray[1] = et2.text.toString().toFloat()
            inputArray[2] = et3.text.toString().toFloat()
            inputArray[3] = et4.text.toString().toFloat()
            inputArray[4] = et5.text.toString().toFloat()
            inputArray[5] = et6.text.toString().toFloat()
            inputArray[6] = et7.text.toString().toFloat()

            val output = runInference(inputArray)
            val result = interpretOutput(output)

        }



    }

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = assets.openFd("model_co2rating.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun runInference(input: FloatArray): FloatArray {
        val inputBuffer = arrayOf(input)
        val outputBuffer = arrayOf(FloatArray(10)) // Sesuaikan dengan ukuran output model (10 kelas)
        tflite.run(inputBuffer, outputBuffer)
        return outputBuffer[0]
    }

    private fun interpretOutput(output: FloatArray): Pair<Int, Float> {
        val maxIndex = output.indices.maxByOrNull { output[it] } ?: -1
        val confidence = output[maxIndex]
        Log.e("result",maxIndex.toString())
        Log.e("Res", confidence.toString())

        if (maxIndex==1){
            resultText.text = "Rating Co2 Kendaraan Ini Buruk"
        }
        else if(maxIndex==2){
            resultText.text = "Rating Co2 Kendaraan Ini Buruk"
        }
        else if(maxIndex==3){
            resultText.text = "Rating Co2 Kendaraan Ini Buruk"
        }
        else if(maxIndex==4){
            resultText.text = "Rating Co2 Kendaraan Kurang Baik"
        }
        else if(maxIndex==5){
            resultText.text = "Rating Co2 Kendaraan Kurang Baik"
        }
        else if(maxIndex==6){
            resultText.text = "Rating Co2 Kendaraan Kurang Baik"
        }
        else if(maxIndex==7){
            resultText.text = "Rating Co2 Kendaraan Ini Baik"
        }
        else if(maxIndex==8){
            resultText.text = "Rating Co2 Kendaraan Ini Baik"
        }
        else if(maxIndex==9){
            resultText.text = "Rating Co2 Kendaraan Ini Sangat Baik"
        }
        else if(maxIndex==10){
            resultText.text = "Rating Co2 Kendaraan Ini Sangat Baik"
        }

        return Pair(maxIndex, confidence)
    }


    class InputFilterMinMax: InputFilter {
        private var min:Int = 0
        private var max:Int = 0
        constructor(min:Int, max:Int) {
            this.min = min
            this.max = max
        }
        constructor(min:String, max:String) {
            this.min = Integer.parseInt(min)
            this.max = Integer.parseInt(max)
        }
        override fun filter(source:CharSequence, start:Int, end:Int, dest: Spanned, dstart:Int, dend:Int): CharSequence? {
            try
            {
                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(min, max, input))
                    return null
            }
            catch (nfe:NumberFormatException) {}
            return ""
        }
        private fun isInRange(a:Int, b:Int, c:Int):Boolean {
            return if (b > a) c in a..b else c in b..a
        }
    }

    fun smog(view: View?) {
        val items = arrayOf("1=Buruk", "2=Buruk", "3=Buruk", "4=Kurang Baik", "5=Kurang Baik", "6=Kurang Baik",
                            "7=Baik", "8=Baik", "8=Sangat Baik", "10=Sangat Baik")
        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()
        }
        with(builder)
        {
            setTitle("List Nilai Smog Rating")
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
            }

            setPositiveButton("OK", positiveButtonClick)
            show()
        }
    }

    fun kelas_kendaraan(view: View?) {
        val items = arrayOf("Compact=0", "SUV: Small=1", "Mid-size=2", "Minicompact=3",
            "SUV: Standard=4", "Two-seater=5", "Subcompact=6", "Station wagon: Small=7",
            "Station wagon: Mid-size=8", "Full-size=9", "Pickup truck: Small=10",
            "Pickup truck: Standard=11", "Minivan=12", "Special purpose vehicle=13")
        val builder = AlertDialog.Builder(this)
        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            Toast.makeText(applicationContext,
                android.R.string.ok, Toast.LENGTH_SHORT).show()
        }
        with(builder)
        {
            setTitle("List Nilai Kelas Kendaraan")
            setItems(items) { dialog, which ->
                Toast.makeText(applicationContext, items[which] + " is clicked", Toast.LENGTH_SHORT).show()
            }

            setPositiveButton("OK", positiveButtonClick)
            show()
        }
    }


}
