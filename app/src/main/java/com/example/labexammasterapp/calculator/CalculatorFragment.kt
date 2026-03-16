// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val num1 = view.findViewById<EditText>(R.id.editNum1)
        val num2 = view.findViewById<EditText>(R.id.editNum2)
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        val txtResult = view.findViewById<TextView>(R.id.txtCalcResult)

        btnAdd.setOnClickListener {
            val v1 = num1.text.toString().toDoubleOrNull() ?: 0.0
            val v2 = num2.text.toString().toDoubleOrNull() ?: 0.0
            // ===== EXAM MODIFICATION AREA =====
            // Change operation here
            val result = v1 + v2
            // ==================================
            txtResult.text = "Result: $result"
        }
    }
}
