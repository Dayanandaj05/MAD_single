// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.broadcast

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class BroadcastFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_broadcast)
        
        val btnSendBroadcast = findViewById<Button>(R.id.btnSendBroadcast)

        btnSendBroadcast.setOnClickListener {
            // ===== EXAM MODIFICATION AREA =====
            // Change custom action name if question specifies
            // ==================================
            val intent = Intent("com.example.CUSTOM_ACTION")
            intent.putExtra("message", "Hello from Broadcast!")
            this.sendBroadcast(intent)
        }
    }
}
