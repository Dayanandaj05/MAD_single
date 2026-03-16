// ==== IMPORTANT FOR COPY-PASTING ====
// If you are copying this file into a NEW project:
// 1. DO NOT copy the package line below. Keep your own package line!
// 2. DO NOT copy the import com.example.labexammasterapp.R line.
// 3. Ensure your layouts match the names used here.
// ===================================

package com.example.labexammasterapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.labexammasterapp.alarm.AlarmFragment
import com.example.labexammasterapp.dialer.DialerFragment
import com.example.labexammasterapp.grade.GradeFragment
import com.example.labexammasterapp.broadcast.BroadcastFragment
import com.example.labexammasterapp.sms.SmsFragment
import com.example.labexammasterapp.service.ServiceFragment
import com.example.labexammasterapp.email.EmailFragment
import com.example.labexammasterapp.contacts.ContactsFragment
import com.example.labexammasterapp.listview.ListViewFragment
import com.example.labexammasterapp.converter.TemperatureConverterFragment
import com.example.labexammasterapp.calculator.CalculatorFragment
import com.example.labexammasterapp.feedback.RatingFragment
import com.example.labexammasterapp.extras.ToastFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the buttons from the layout
        val btnAlarm = findViewById<Button>(R.id.btnAlarm)
        val btnDialer = findViewById<Button>(R.id.btnDialer)
        val btnGrade = findViewById<Button>(R.id.btnGrade)
        val btnBroadcast = findViewById<Button>(R.id.btnBroadcast)
        val btnSms = findViewById<Button>(R.id.btnSms)
        val btnService = findViewById<Button>(R.id.btnService)
        val btnEmail = findViewById<Button>(R.id.btnEmail)
        val btnContacts = findViewById<Button>(R.id.btnContacts)
        val btnListView = findViewById<Button>(R.id.btnListView)
        val btnConverter = findViewById<Button>(R.id.btnConverter)
        val btnCalculator = findViewById<Button>(R.id.btnCalculator)
        val btnRating = findViewById<Button>(R.id.btnRating)
        val btnToast = findViewById<Button>(R.id.btnToast)

        // ===== EXAM MODIFICATION AREA =====
        // Delete or comment out buttons you do NOT need for your exam.
        // Change the fragment being loaded to match the exam question.
        
        btnAlarm.setOnClickListener { loadFragment(AlarmFragment()) }
        btnDialer.setOnClickListener { loadFragment(DialerFragment()) }
        btnGrade.setOnClickListener { loadFragment(GradeFragment()) }
        btnBroadcast.setOnClickListener { loadFragment(BroadcastFragment()) }
        btnSms.setOnClickListener { loadFragment(SmsFragment()) }
        btnService.setOnClickListener { loadFragment(ServiceFragment()) }
        btnEmail.setOnClickListener { loadFragment(EmailFragment()) }
        btnContacts.setOnClickListener { loadFragment(ContactsFragment()) }
        btnListView.setOnClickListener { loadFragment(ListViewFragment()) }
        btnConverter.setOnClickListener { loadFragment(TemperatureConverterFragment()) }
        btnCalculator.setOnClickListener { loadFragment(CalculatorFragment()) }
        btnRating.setOnClickListener { loadFragment(RatingFragment()) }
        btnToast.setOnClickListener { loadFragment(ToastFragment()) }
        // ==================================
    }

    // Helper function to easily swap out screens (Fragments)
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
