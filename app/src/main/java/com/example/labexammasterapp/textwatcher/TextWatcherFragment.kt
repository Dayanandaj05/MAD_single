// ==== IMPORTANT FOR COPY-PASTING ====
// 1. DO NOT copy the package line. Keep your own!
// 2. DO NOT copy import com.example.labexammasterapp.R
// 3. Ensure layout XML names match.
// ===================================

package com.example.labexammasterapp.textwatcher

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class TextWatcherFragment : Fragment(R.layout.fragment_text_watcher) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val tvLivePreview = view.findViewById<TextView>(R.id.tvLivePreview)
        val etNumber1 = view.findViewById<EditText>(R.id.etNumber1)
        val etNumber2 = view.findViewById<EditText>(R.id.etNumber2)
        val btnSendData = view.findViewById<Button>(R.id.btnSendData)

        // ===== EXAM MODIFICATION AREA =====
        // addTextChangedListener: 3 methods must ALL be implemented (even if empty)
        //   - beforeTextChanged: fires BEFORE the change
        //   - onTextChanged: fires DURING the change — use 's' parameter here
        //   - afterTextChanged: fires AFTER the change — use 's: Editable?' here
        // setOnEditorActionListener: catches keyboard action button presses
        //   - IME_ACTION_NEXT = the 'Next' button on keyboard
        //   - IME_ACTION_DONE = the 'Done' button — use to hide keyboard or trigger submit
        //   - Must return true to consume the event, false to let system handle it
        // ==================================

        etNumber1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // ===== EXAM MOD: Change tvLivePreview.text assignment to do live calculation instead =====
                tvLivePreview.text = s.toString()
            }
            
            override fun afterTextChanged(s: Editable?) {}
        })

        etNumber1.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                etNumber2.requestFocus()
                true
            } else false
        }

        btnSendData.setOnClickListener {
            val s1 = etNumber1.text.toString().trim()
            val s2 = etNumber2.text.toString().trim()
            if (s1.isEmpty() || s2.isEmpty()) { 
                Toast.makeText(requireContext(), "Fill both fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener 
            }
            
            val intent = Intent(requireContext(), TextWatcherResultActivity::class.java)
            intent.putExtra("NUM1", s1)
            intent.putExtra("NUM2", s2)
            startActivity(intent)
        }
    }
}
