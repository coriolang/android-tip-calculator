package com.coriolang.tipcalculator

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {

    private var bill: Double = 0.0
    private var tip: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val slider = findViewById<Slider>(R.id.slider)
        val textView = findViewById<TextView>(R.id.text_view)

        editText.doAfterTextChanged {
            if (editText.text.isEmpty()) {
                textView.text = ""

                return@doAfterTextChanged
            }

            updateTipAmount(editText, slider, textView)
        }

        slider.addOnChangeListener { _, _, _ ->
            if (editText.text != null) {
                if (editText.text.isEmpty()) {
                    return@addOnChangeListener
                }
            }

            updateTipAmount(editText, slider, textView)
        }
    }

    private fun updateTipAmount(editText: EditText, slider: Slider, textView: TextView) {
        bill = editText.text.toString().toDouble()
        tip = slider.value.toInt().toString().toInt()

        val result = calculateTipAmount(bill, tip)

        textView.text = getTipAmountString(result)
    }

    private fun calculateTipAmount(bill: Double, tip: Int) = (bill * tip) / 100

    private fun getTipAmountString(result: Double) = "Tip amount: %.2f".format(result)
}