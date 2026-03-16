// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.contacts

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class ContactsFragment : Fragment(R.layout.fragment_contacts) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLoad = view.findViewById<Button>(R.id.btnLoadContacts)
        val txtContacts = view.findViewById<TextView>(R.id.txtContacts)

        btnLoad.setOnClickListener {
            val cursor: Cursor? = requireContext().contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null
            )
            val builder = StringBuilder()
            
            // ===== EXAM MODIFICATION AREA =====
            // Loop through contacts (max 5 output for simplicity)
            var count = 0
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    val nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
                    val phoneIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    val name = cursor.getString(nameIdx)
                    val number = cursor.getString(phoneIdx)
                    builder.append("Name: $name | Num: $number\n")
                    count++
                } while (cursor.moveToNext() && count < 5)
                cursor.close()
            }
            // ==================================
            
            if (builder.isEmpty()) txtContacts.text = "No contacts found / Permission missing"
            else txtContacts.text = builder.toString()
        }
    }
}
