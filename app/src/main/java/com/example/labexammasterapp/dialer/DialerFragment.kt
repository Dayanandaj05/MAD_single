// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.dialer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class DialerFragment : Fragment(R.layout.fragment_dialer) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Find the input box and button from the layout
        val editPhone = view.findViewById<EditText>(R.id.editPhone)
        val btnCall = view.findViewById<Button>(R.id.btnCall)

        // When the button is clicked, run this code
        btnCall.setOnClickListener {
            // Get the phone number typed by the user
            val number = editPhone.text.toString()

            // ===== EXAM MODIFICATION AREA =====
            // Change ACTION_DIAL to ACTION_CALL if the exam asks to call directly
            // (Note: ACTION_CALL requires CALL_PHONE permission in Manifest)
            // ==================================

            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }
    }
}
