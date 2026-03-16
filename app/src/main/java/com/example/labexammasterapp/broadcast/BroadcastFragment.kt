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
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class BroadcastFragment : Fragment(R.layout.fragment_broadcast) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val editCustomMessage = view.findViewById<EditText>(R.id.editCustomMessage)
        val btnSendBroadcast = view.findViewById<Button>(R.id.btnSendBroadcast)

        btnSendBroadcast.setOnClickListener {
            val message = editCustomMessage.text.toString()

            // ===== EXAM MODIFICATION AREA =====
            // Change the action string to whatever the question asks for
            // e.g., "com.myexam.CUSTOM_EVENT"
            // Make sure the action name matched exactly inside AndroidManifest.xml
            // ==================================

            val intent = Intent("com.example.CUSTOM_ACTION")
            intent.putExtra("MESSAGE", message)
            intent.setPackage(requireContext().packageName) // Needed for Android 8.0+ implicit broadcasts
            
            requireContext().sendBroadcast(intent)
        }
    }
}
