package com.example.labexammasterapp.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras ?: return
        val pdus = bundle.get("pdus") as Array<*>
        for (pdu in pdus) {
            val sms     = SmsMessage.createFromPdu(pdu as ByteArray)
            val sender  = sms.displayOriginatingAddress
            val message = sms.displayMessageBody

            // ===== EXAM MODIFICATION AREA =====
            Toast.makeText(context, "SMS from $sender: $message", Toast.LENGTH_LONG).show()
            // ==================================

            // Send local broadcast so SmsFragment can display it on screen
            val localIntent = Intent("SMS_DISPLAY_ACTION")
            localIntent.putExtra("sender", sender)
            localIntent.putExtra("message", message)
            context.sendBroadcast(localIntent)
        }
    }
}
