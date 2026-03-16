// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.converter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class TemperatureConverterFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_converter)
        
        val editCelsius = findViewById<EditText>(R.id.editCelsius)
        val btnConvert = findViewById<Button>(R.id.btnConvert)
        val txtFahrenheit = findViewById<TextView>(R.id.txtFahrenheit)

        btnConvert.setOnClickListener {
            val celsiusText = editCelsius.text.toString()
            val celsius = celsiusText.toDoubleOrNull() ?: 0.0

            // ===== EXAM MODIFICATION AREA =====
            val fahrenheit = (celsius * 9 / 5) + 32
            // ==================================

            txtFahrenheit.text = "Fahrenheit: $fahrenheit"
        }
    }
}
