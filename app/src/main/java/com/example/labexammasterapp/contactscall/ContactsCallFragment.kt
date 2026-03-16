// ==== IMPORTANT FOR COPY-PASTING ====
// 1. DO NOT copy the package line. Keep your own!
// 2. DO NOT copy import com.example.labexammasterapp.R
// 3. Ensure layout XML names match.
// ===================================

package com.example.labexammasterapp.contactscall

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class ContactsCallFragment : Fragment(R.layout.fragment_contacts_call) {

    private lateinit var lvContactsCall: ListView
    private var phoneNumberToCall: String = ""
    private val cols = arrayOf(
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, 
        ContactsContract.CommonDataKinds.Phone.NUMBER, 
        ContactsContract.CommonDataKinds.Phone._ID
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        lvContactsCall = view.findViewById(R.id.lvContactsCall)

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 301)
        } else {
            loadContacts()
        }

        lvContactsCall.setOnItemClickListener { parent, _, position, _ ->
            val cursor = parent.adapter.getItem(position) as Cursor
            phoneNumberToCall = cursor.getString(cursor.getColumnIndexOrThrow("data1")) // data1 matches NUMBER column
            attemptCall()
        }
    }

    private fun loadContacts() {
        // ===== EXAM MODIFICATION AREA =====
        // SimpleCursorAdapter key difference from ArrayAdapter:
        //   - Works directly with a Cursor (database query result)
        //   - 'from' = column names from the cursor to read
        //   - 'to' = view IDs in the row layout to put them in
        //   - android.R.layout.simple_list_item_2 shows 2 lines per row (text1=name, text2=number)
        // Two-step permission: READ_CONTACTS first, then CALL_PHONE only when user taps a contact
        // ==================================
        val from = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER)
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        
        val cursor = requireContext().contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI, cols, null, null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
        )
        
        val adapter = SimpleCursorAdapter(requireContext(), android.R.layout.simple_list_item_2, cursor, from, to, 0)
        lvContactsCall.adapter = adapter
    }

    private fun attemptCall() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), 302)
        } else {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumberToCall")
            startActivity(callIntent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 301) {
                loadContacts()
            } else if (requestCode == 302) {
                attemptCall()
            }
        } else {
            Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
