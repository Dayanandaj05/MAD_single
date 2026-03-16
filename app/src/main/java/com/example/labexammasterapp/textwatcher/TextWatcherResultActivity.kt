// ==== IMPORTANT FOR COPY-PASTING ====
// 1. DO NOT copy the package line. Keep your own!
// 2. DO NOT copy import com.example.labexammasterapp.R
// 3. Ensure layout XML names match.
// ===================================

package com.example.labexammasterapp.textwatcher

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class TextWatcherResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_watcher_result)

        val s1 = intent.getStringExtra("NUM1").toString()
        val s2 = intent.getStringExtra("NUM2").toString()
        val v1 = s1.toIntOrNull() ?: 0
        val v2 = s2.toIntOrNull() ?: 0
        
        // ===== EXAM MOD: Change + to -, *, / for different operations =====
        val result = v1 + v2
        
        val tvResultDisplay = findViewById<TextView>(R.id.tvResultDisplay)
        tvResultDisplay.text = "Result: $result"
        Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show()
    }
}
