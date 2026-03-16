// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.feedback

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class RatingFragment : Fragment(R.layout.fragment_rating) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val ratingBar = view.findViewById<RatingBar>(R.id.ratingBar)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmitRating)

        btnSubmit.setOnClickListener {
            // ===== EXAM MODIFICATION AREA =====
            val stars = ratingBar.rating
            Toast.makeText(requireContext(), "You rated: $stars stars", Toast.LENGTH_SHORT).show()
            // ==================================
        }
    }
}
