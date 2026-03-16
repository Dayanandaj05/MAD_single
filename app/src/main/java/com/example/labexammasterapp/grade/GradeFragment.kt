// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.grade

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class GradeFragment : Fragment(R.layout.fragment_grade) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val editMarks = view.findViewById<EditText>(R.id.editMarks)
        val btnCalculate = view.findViewById<Button>(R.id.btnCalculate)
        val txtResult = view.findViewById<TextView>(R.id.txtResult)

        btnCalculate.setOnClickListener {
            // Read what the user typed and convert it to a Number
            val markText = editMarks.text.toString()
            val marks = markText.toIntOrNull()

            if (marks == null) {
                txtResult.text = "Please enter valid marks"
                return@setOnClickListener
            }

            // ===== EXAM MODIFICATION AREA =====
            // Change these grade boundaries to match exam question
            // Example: "If marks > 90 print A+"
            // ==================================
            val grade = if (marks >= 90) {
                "A+"
            } else if (marks >= 80) {
                "A"
            } else if (marks >= 70) {
                "B"
            } else if (marks >= 60) {
                "C"
            } else {
                "Fail"
            }

            txtResult.text = "Grade: $grade"
        }
    }
}
