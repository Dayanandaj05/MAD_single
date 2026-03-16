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
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class ServiceFragment : Fragment(R.layout.fragment_service) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnStart = view.findViewById<Button>(R.id.btnStartService)
        val btnStop = view.findViewById<Button>(R.id.btnStopService)

        btnStart.setOnClickListener {
            val intent = Intent(requireContext(), MyService::class.java)
            requireContext().startService(intent)
        }
        btnStop.setOnClickListener {
            val intent = Intent(requireContext(), MyService::class.java)
            requireContext().stopService(intent)
        }
    }
}
