// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.listview

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class ListViewFragment : Fragment(R.layout.fragment_listview) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        val listView = view.findViewById<ListView>(R.id.myListView)

        // ===== EXAM MODIFICATION AREA =====
        val dataList = arrayOf("Apple", "Banana", "Cherry", "Mango", "Orange")
        // ==================================

        // ArrayAdapter converts our text array into visual list items
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, // Default Android row layout
            dataList
        )
        
        listView.adapter = adapter

        // Listen for when a user clicks an item
        listView.setOnItemClickListener { parent, itemView, position, id ->
            // ===== EXAM MODIFICATION AREA =====
            val clickedItem = dataList[position]
            Toast.makeText(requireContext(), "You clicked: $clickedItem", Toast.LENGTH_SHORT).show()
            // ==================================
        }
    }
}
