// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.email

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class EmailFragment : Fragment(R.layout.fragment_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editEmail = view.findViewById<EditText>(R.id.editEmail)
        val editSubject = view.findViewById<EditText>(R.id.editSubject)
        val editMessage = view.findViewById<EditText>(R.id.editMessage)
        val btnSend = view.findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {
            val emailAddress = editEmail.text.toString()
            val subject = editSubject.text.toString()
            val message = editMessage.text.toString()

            // ===== EXAM MODIFICATION AREA =====
            // If hardcoded email needed: val emailAddress = "teacher@school.edu"
            // ==================================

            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain" 
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailAddress))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(intent, "Choose Email App"))
        }
    }
}
