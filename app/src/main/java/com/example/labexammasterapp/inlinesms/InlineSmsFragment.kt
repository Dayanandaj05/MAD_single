// ==== IMPORTANT FOR COPY-PASTING ====
// 1. DO NOT copy the package line. Keep your own!
// 2. DO NOT copy import com.example.labexammasterapp.R
// 3. Ensure layout XML names match.
// ===================================

package com.example.labexammasterapp.inlinesms

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.Telephony
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.labexammasterapp.R

class InlineSmsFragment : AppCompatActivity() {

    private var smsReceiver: BroadcastReceiver? = null
    private lateinit var tvStatus: TextView
    private lateinit var tvSmsContent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_inline_sms)
        
        
        tvStatus = findViewById(R.id.tvStatus)
        tvSmsContent = findViewById(R.id.tvSmsContent)

        // Check permission before setting up
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECEIVE_SMS), 200)
        }
    }

    private fun setupSmsReceiver() {
        // ===== EXAM MODIFICATION AREA =====
        // This is the INLINE pattern — receiver is created inside the Activity/Fragment
        // Key difference from SmsReceiver.kt: no separate class file, no Manifest entry needed for receiver
        // Use onResume/onPause to register/unregister so it only runs when screen is visible
        // ==================================
        
        smsReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == "android.provider.Telephony.SMS_RECEIVED") {
                    val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)
                    for (sms in messages) {
                        val sender = sms.displayOriginatingAddress
                        val body = sms.displayMessageBody
                        
                        tvStatus.text = "SMS Received from: $sender"
                        tvSmsContent.text = body
                        Toast.makeText(this, body, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        
        val intentFilter = IntentFilter("android.provider.Telephony.SMS_RECEIVED")
        this.registerReceiver(smsReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED) {
            setupSmsReceiver()
        }
    }

    override fun onPause() {
        super.onPause()
        smsReceiver?.let { this.unregisterReceiver(it) }
        smsReceiver = null
    }
}
