package com.example.labexammasterapp.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class CalculatorFragment : Fragment(R.layout.fragment_calculator) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val num1      = view.findViewById<EditText>(R.id.editNum1)
        val num2      = view.findViewById<EditText>(R.id.editNum2)
        val btnAdd    = view.findViewById<Button>(R.id.btnAdd)
        val btnSub    = view.findViewById<Button>(R.id.btnSub)
        val btnMul    = view.findViewById<Button>(R.id.btnMul)
        val btnDiv    = view.findViewById<Button>(R.id.btnDiv)
        val txtResult = view.findViewById<TextView>(R.id.txtCalcResult)

        fun getValues(): Pair<Double, Double>? {
            val v1 = num1.text.toString().toDoubleOrNull()
            val v2 = num2.text.toString().toDoubleOrNull()
            if (v1 == null || v2 == null) {
                Toast.makeText(requireContext(), "Enter valid numbers!", Toast.LENGTH_SHORT).show()
                return null
            }
            return Pair(v1, v2)
        }

        // ===== EXAM MODIFICATION AREA =====
        btnAdd.setOnClickListener {
            val (v1, v2) = getValues() ?: return@setOnClickListener
            txtResult.text = "Result: ${v1 + v2}"
        }
        btnSub.setOnClickListener {
            val (v1, v2) = getValues() ?: return@setOnClickListener
            txtResult.text = "Result: ${v1 - v2}"
        }
        btnMul.setOnClickListener {
            val (v1, v2) = getValues() ?: return@setOnClickListener
            txtResult.text = "Result: ${v1 * v2}"
        }
        btnDiv.setOnClickListener {
            val (v1, v2) = getValues() ?: return@setOnClickListener
            if (v2 == 0.0) {
                Toast.makeText(requireContext(), "Cannot divide by zero!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            txtResult.text = "Result: ${v1 / v2}"
        }
        // ==================================
    }
}
