// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.extras

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class ToastFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_toast)
        
        val btnShowToast = findViewById<Button>(R.id.btnShowToast)

        btnShowToast.setOnClickListener {
            // ===== EXAM MODIFICATION AREA =====
            Toast.makeText(this, "Simple Toast Message!", Toast.LENGTH_SHORT).show()
            // ==================================
        }
    }
}
