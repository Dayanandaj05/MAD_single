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
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class ListViewFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_listview)
        
        val listView = findViewById<ListView>(R.id.listViewItems)

        // ===== EXAM MODIFICATION AREA =====
        // Change array items to what question specifies
        val items = arrayOf("Apple", "Banana", "Orange", "Grapes", "Mango")
        // ==================================

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Clicked: ${items[position]}", Toast.LENGTH_SHORT).show()
        }
    }
}
