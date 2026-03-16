// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.service

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class ServiceFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_service)
        
        val btnStart = findViewById<Button>(R.id.btnStartService)
        val btnStop = findViewById<Button>(R.id.btnStopService)

        btnStart.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            this.startService(intent)
        }
        btnStop.setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            this.stopService(intent)
        }
    }
}
