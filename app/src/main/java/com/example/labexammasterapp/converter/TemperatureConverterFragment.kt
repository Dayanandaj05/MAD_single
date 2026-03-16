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
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class TemperatureConverterFragment : Fragment(R.layout.fragment_converter) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val editCelsius = view.findViewById<EditText>(R.id.editCelsius)
        val btnConvert = view.findViewById<Button>(R.id.btnConvert)
        val txtFahrenheit = view.findViewById<TextView>(R.id.txtFahrenheit)

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
