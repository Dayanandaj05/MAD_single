// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.R

class AlarmFragment : Fragment(R.layout.fragment_alarm) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editSeconds = view.findViewById<EditText>(R.id.editSeconds)
        val btnSetAlarm = view.findViewById<Button>(R.id.btnSetAlarm)

        btnSetAlarm.setOnClickListener {
            val seconds = editSeconds.text.toString().toIntOrNull() ?: 5

            // Setup Intent to alarm receiver
            val intent = Intent(requireContext(), AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )

            // ===== EXAM MODIFICATION AREA =====
            // Change the delay time or the alarm type below
            // ==================================
            val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val triggerTime = System.currentTimeMillis() + (seconds * 1000)
            
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent)
            Toast.makeText(context, "Alarm set for $seconds seconds", Toast.LENGTH_SHORT).show()
        }
    }
}
