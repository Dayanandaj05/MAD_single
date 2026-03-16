// ==== IMPORTANT FOR COPY-PASTING ====
// 1. DO NOT copy the package line. Keep your own!
// 2. DO NOT copy import com.example.labexammasterapp.R
// 3. Ensure layout XML names match.
// ===================================

package com.example.labexammasterapp.snackbartouch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R
import com.google.android.material.snackbar.Snackbar

class SnackbarTouchFragment : Fragment(R.layout.fragment_snackbar_touch) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val tvSnackTarget = view.findViewById<TextView>(R.id.tvSnackTarget)
        val tvTouchTarget = view.findViewById<TextView>(R.id.tvTouchTarget)
        val tvTouchStatus = view.findViewById<TextView>(R.id.tvTouchStatus)
        val btnOpenUrl = view.findViewById<Button>(R.id.btnOpenUrl)

        // ===== EXAM MODIFICATION AREA =====
        // Snackbar vs Toast:
        //   Toast: fire-and-forget popup. No action button possible.
        //   Snackbar: appears at bottom, CAN have a setAction() button for undo/dismiss
        //   Snackbar needs a View reference (pass 'it' from the click listener)
        // setOnLongClickListener: must return true to tell Android you handled it
        // setOnTouchListener: ACTION_DOWN=finger touches, ACTION_MOVE=finger slides, ACTION_UP=finger lifts
        //   Must return true to receive MOVE and UP events after DOWN
        // ==================================

        tvSnackTarget.setOnClickListener {
            val snack = Snackbar.make(it, "This is a Snackbar", Snackbar.LENGTH_LONG)
            snack.setAction("DISMISS") { tvSnackTarget.text = "Snackbar dismissed!" }
            snack.show()
            // ===== EXAM MOD: Change message text, action label, and action behavior =====
        }

        tvSnackTarget.setOnLongClickListener {
            tvSnackTarget.textSize = 30f
            Toast.makeText(requireContext(), "Long press detected!", Toast.LENGTH_SHORT).show()
            true  // must return true to consume the event
            // ===== EXAM MOD: Change textSize value or do something else on long press =====
        }

        tvTouchTarget.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    tvSnackTarget.textSize = 30f
                    tvTouchStatus.text = "Touch status: DOWN (finger placed)"
                }
                MotionEvent.ACTION_MOVE -> {
                    tvSnackTarget.textSize = 50f
                    tvTouchStatus.text = "Touch status: MOVE (finger dragging)"
                }
                MotionEvent.ACTION_UP -> {
                    tvSnackTarget.textSize = 70f
                    tvTouchStatus.text = "Touch status: UP (finger lifted)"
                }
            }
            true
            // ===== EXAM MOD: Change what happens on each touch action — resize, color change, etc. =====
        }

        btnOpenUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://www.psgtech.edu/")
            startActivity(intent)
            // ===== EXAM MOD: Change the URL to whatever the question specifies =====
        }
    }
}
