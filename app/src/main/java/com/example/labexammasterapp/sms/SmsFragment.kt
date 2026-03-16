package com.example.labexammasterapp.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class SmsFragment : Fragment(R.layout.fragment_sms) {

    private var displayReceiver: BroadcastReceiver? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tvSmsDisplay = view.findViewById<TextView>(R.id.tvSmsDisplay)
        tvSmsDisplay.text =
            "Waiting for SMS...\n\nTo test on emulator:\n" +
            "1. Click '...' on emulator side panel\n" +
            "2. Go to Phone tab\n" +
            "3. Type a number and message\n" +
            "4. Click Send Message"
    }

    override fun onResume() {
        super.onResume()
        displayReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val sender  = intent?.getStringExtra("sender")  ?: "Unknown"
                val message = intent?.getStringExtra("message") ?: ""
                view?.findViewById<TextView>(R.id.tvSmsDisplay)?.text =
                    "SMS Received!\n\nFrom: $sender\n\nMessage:\n$message"
            }
        }
        requireContext().registerReceiver(displayReceiver, IntentFilter("SMS_DISPLAY_ACTION"))
    }

    override fun onPause() {
        super.onPause()
        displayReceiver?.let { requireContext().unregisterReceiver(it) }
        displayReceiver = null
    }
}
