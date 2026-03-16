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
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class GradeFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_grade)
        

        val editMarks = findViewById<EditText>(R.id.editMarks)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        btnCalculate.setOnClickListener {
            val marksText = editMarks.text.toString()
            val marks = marksText.toIntOrNull() ?: 0

            // ===== EXAM MODIFICATION AREA =====
            // Change the grading rules here if the question gives different marks
            var grade = ""
            if (marks >= 90) { grade = "A" }
            else if (marks >= 80) { grade = "B" }
            else if (marks >= 70) { grade = "C" }
            else { grade = "Fail" }
            // ==================================

            txtResult.text = "Your Grade: $grade"
        }
    }
}
