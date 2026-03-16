// ==== IMPORTANT FOR COPY-PASTING ====
// 1. DO NOT copy the package line. Keep your own!
// 2. DO NOT copy import com.example.labexammasterapp.R
// 3. Ensure layout XML names match.
// ===================================

package com.example.labexammasterapp.contextmenu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class ContextMenuFragment : AppCompatActivity() {

    private lateinit var tvPhoneNumber: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_context_menu)
        
        
        // ===== EXAM MODIFICATION AREA =====
        // Context Menu = menu that appears on LONG PRESS (not a button press)
        // Steps:
        //   1. Call registerForContextMenu(view) on the view you want to long-press
        //   2. Override onCreateContextMenu — inflate your menu XML here
        //   3. Override onContextItemSelected — handle each item click here
        // Different from Options Menu (3-dot menu in toolbar):
        //   Options Menu → onCreateOptionsMenu + onOptionsItemSelected
        //   Context Menu → onCreateContextMenu + onContextItemSelected (triggered by long press on a specific view)
        // isAppInstalled() — reusable pattern to check if any app exists before opening it
        // ==================================

        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        registerForContextMenu(tvPhoneNumber)
        // ===== EXAM MOD: Change the number in the TextView or make it an EditText for user input =====
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        this.menuInflater.inflate(R.menu.context_menu, menu)
        menu.setHeaderTitle("Choose action for: ${tvPhoneNumber.text}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val number = tvPhoneNumber.text.toString()
        return when (item.itemId) {
            R.id.ctx_call -> {
                makeCall(number)
                true
            }
            R.id.ctx_sms -> {
                // Open SMS app with number pre-filled
                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.data = Uri.parse("sms:$number")
                startActivity(smsIntent)
                true
            }
            R.id.ctx_whatsapp -> {
                openWhatsApp(number)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun makeCall(number: String) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 401)
        } else {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:$number")
            startActivity(intent)
        }
    }

    private fun openWhatsApp(number: String) {
        val isInstalled = isAppInstalled("com.whatsapp") || isAppInstalled("com.whatsapp.w4b")
        if (isInstalled) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://api.whatsapp.com/send?phone=91" + number + "&text=Hello!")
            startActivity(intent)
        } else {
            Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show()
        }
        // ===== EXAM MOD: Change &text=Hello! to whatever pre-filled message the question wants =====
    }

    private fun isAppInstalled(packageName: String): Boolean {
        return try {
            this.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
        // ===== EXAM MOD: This function checks if ANY app is installed — reuse it for other apps too =====
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 401 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            makeCall(tvPhoneNumber.text.toString())
        } else {
            Toast.makeText(this, "Call permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
