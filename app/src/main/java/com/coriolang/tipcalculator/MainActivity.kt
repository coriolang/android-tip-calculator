package com.coriolang.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val slider = findViewById<Slider>(R.id.slider)
        val textView = findViewById<TextView>(R.id.text_view)

        var bill = ""
        var tip: String

        editText.doAfterTextChanged {
            if (editText.text.isEmpty()) {
                textView.text = ""

                return@doAfterTextChanged
            }

            bill = editText.text.toString()
            tip = slider.value.toInt().toString()
            textView.text = getEnteredDataAsString(bill, tip)
        }

        slider.addOnChangeListener { _, value, _ ->
            if (editText.text != null) {
                if (editText.text.isEmpty()) {
                    return@addOnChangeListener
                }
            }

            tip = value.toInt().toString()
            textView.text = getEnteredDataAsString(bill, tip)
        }
    }

    private fun getEnteredDataAsString(
        bill: String,
        tip: String
    ) = "Bill value: $bill, tip percentage: $tip%"
}