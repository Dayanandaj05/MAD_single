# LabExamMasterApp 🎓 - Android Lab Exam Survival Template

> PSG College of Technology · MCA Mobile Application Development Lab  
> A single Android app with 13 ready-to-use modules for every common exam question type.

---

## Table of Contents
1. [Project Structure](#project-structure)
2. [How to Run](#how-to-run)
3. [Exam Copy-Paste Rules](#exam-copy-paste-rules)
4. [Module Index](#module-index)
5. [AndroidManifest Permissions Guide](#androidmanifest-permissions-guide)
6. [Android Components Explained](#android-components-explained)
7. [XML Layout Cheat Sheet](#xml-layout-cheat-sheet)
8. [Kotlin Patterns](#kotlin-patterns)
9. [Suggested Improvements](#suggested-improvements)
10. [Common Errors and Fixes](#common-errors-and-fixes)

---

## Project Structure
```
LabExamMasterApp/
├── app/src/main/
│   ├── java/com/example/labexammasterapp/
│   │   ├── MainActivity.kt              ← Entry point. Shows nav buttons, swaps Fragments
│   │   ├── alarm/
│   │   │   ├── AlarmFragment.kt         ← Set a timed alarm
│   │   │   └── AlarmReceiver.kt         ← Fires when alarm triggers
│   │   ├── broadcast/
│   │   │   ├── BroadcastFragment.kt     ← Sends a custom broadcast
│   │   │   └── BroadcastReceiverExample.kt ← Receives it
│   │   ├── calculator/
│   │   │   └── CalculatorFragment.kt    ← Basic math
│   │   ├── contacts/
│   │   │   └── ContactsFragment.kt      ← Reads phone contacts
│   │   ├── converter/
│   │   │   └── TemperatureConverterFragment.kt ← Celsius ↔ Fahrenheit
│   │   ├── dialer/
│   │   │   └── DialerFragment.kt        ← Open phone dialer / call directly
│   │   ├── email/
│   │   │   └── EmailFragment.kt         ← Compose and send email
│   │   ├── extras/
│   │   │   └── ToastFragment.kt         ← Toast, Snackbar, TextWatcher patterns
│   │   ├── feedback/
│   │   │   └── RatingFragment.kt        ← RatingBar (stars)
│   │   ├── grade/
│   │   │   └── GradeFragment.kt         ← Marks → Grade calculator
│   │   ├── listview/
│   │   │   └── ListViewFragment.kt      ← Scrollable list
│   │   ├── service/
│   │   │   ├── MyService.kt             ← Background service
│   │   │   └── ServiceFragment.kt       ← Start/Stop service UI
│   │   └── sms/
│   │       ├── SmsFragment.kt           ← SMS screen
│   │       └── SmsReceiver.kt           ← Intercepts incoming SMS
│   ├── res/
│   │   ├── layout/
│   │   │   ├── activity_main.xml        ← Main screen with nav buttons
│   │   │   └── fragment_*.xml           ← One XML per module
│   │   ├── values/
│   │   │   ├── strings.xml              ← App text strings
│   │   │   └── themes.xml               ← App theme/colours
│   │   ├── menu/                        ← CREATE THIS for Options Menu questions
│   │   └── raw/                         ← PUT .mp3 FILES HERE for music service
│   └── AndroidManifest.xml              ← Registers every Activity/Service/Receiver + permissions
├── build.gradle.kts                     ← SDK versions and library dependencies
└── settings.gradle.kts                  ← Gradle plugin sources
```

---

## How to Run

### First Time Setup
1. Open Android Studio → click **Open** (NOT New Project) → select the `LabExamMasterApp` folder
2. Wait for **Gradle sync** to finish (progress bar at the bottom, takes 1–3 min first time)
3. If sync fails: **File → Invalidate Caches → Invalidate and Restart**, then sync again

### Run on Emulator
1. **Tools → Device Manager → Create Virtual Device**
2. Choose **Pixel 6** → Next → pick **API 34** (download if needed) → Finish
3. Start the emulator (green play button next to it)
4. Select your emulator in the device dropdown (top-right toolbar)
5. Click the green **Run** button or press `Shift+F10`

### Run on Real Phone
1. Phone: **Settings → About Phone → tap Build Number 7 times** → Developer Options unlocked
2. **Settings → Developer Options → USB Debugging ON**
3. Connect via USB → allow "Trust this computer" on phone
4. Your phone appears in the device dropdown → click Run

### Testing SMS on Emulator
- Click the `...` button on the emulator side panel → **Phone tab** → type a number + message → **Send Message**
- This simulates an incoming SMS to the emulator

---

## Exam Copy-Paste Rules

> ⚠️ Break any of these 3 rules and your app will NOT compile.

### Rule 1 — NEVER copy the package line
```kotlin
// This line is at the top of every file in this template:
package com.example.labexammasterapp

// Your new project has its own package. KEEP YOURS. Delete the one above when pasting.
package com.yourname.myapp  // ← keep this
```

### Rule 2 — NEVER copy the R import
```kotlin
// DELETE this line when pasting into your project:
import com.example.labexammasterapp.R

// Android Studio auto-generates your own R file. Just remove that import line.
```

### Rule 3 — Layout file names must match
If the Fragment says `R.layout.fragment_dialer`, you **must** have a file named `fragment_dialer.xml` in your `res/layout/` folder. Either copy the XML with the exact same name, or change the name in the Kotlin code.

---

### How to Convert a Fragment into a Standalone Activity (Most Exam Questions)

Most exam questions need ONE screen, not a Fragment. Here is how to convert:
```kotlin
// ─── BEFORE (Fragment style — from this template) ───────────────
class GradeFragment : Fragment(R.layout.fragment_grade) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editMarks = view.findViewById<EditText>(R.id.editMarks)  // has view. prefix
    }
}

// ─── AFTER (Activity style — use this in your exam) ─────────────
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_grade)          // same XML is fine
        val editMarks = findViewById<EditText>(R.id.editMarks)  // no view. prefix
    }
}
```

**Conversion checklist:**
- [ ] Change `: Fragment(R.layout.xxx)` → `: AppCompatActivity()`
- [ ] Change `onViewCreated(view, ...)` → `onCreate(savedInstanceState)`
- [ ] Add `super.onCreate(savedInstanceState)` and `setContentView(R.layout.xxx)` at top
- [ ] Remove all `view.` prefixes from `view.findViewById(...)`
- [ ] Change `requireContext()` → `this`
- [ ] Change `context` → `this`

---

## Module Index

### Keyword → Module mapping

| Exam keyword | Module folder | Files to use |
|---|---|---|
| Alarm, Reminder, Schedule, Timer | `alarm/` | AlarmFragment.kt, AlarmReceiver.kt |
| Broadcast, Custom action, Signal | `broadcast/` | BroadcastFragment.kt, BroadcastReceiverExample.kt |
| Calculator, Add, Subtract, Arithmetic | `calculator/` | CalculatorFragment.kt |
| Contacts, Phone book, Address book | `contacts/` | ContactsFragment.kt |
| Convert, Temperature, Celsius, Unit | `converter/` | TemperatureConverterFragment.kt |
| Call, Dial, Phone number | `dialer/` | DialerFragment.kt |
| Email, Send mail, Compose | `email/` | EmailFragment.kt |
| Toast, Snackbar, TextWatcher, Validation | `extras/` | ToastFragment.kt |
| Rating, Stars, Feedback, Review | `feedback/` | RatingFragment.kt |
| Grade, Marks, Pass/Fail, GPA | `grade/` | GradeFragment.kt |
| List, ListView, Display multiple items | `listview/` | ListViewFragment.kt |
| Service, Background, Music, Keep playing | `service/` | MyService.kt, ServiceFragment.kt |
| SMS, Text message, OTP, Auto-fill | `sms/` | SmsReceiver.kt, SmsFragment.kt |

---

### Module 1 — Alarm (`alarm/`)
**What it does:** User types a delay in seconds. After that time, `AlarmReceiver` fires and shows a Toast.

| Concept | Explanation |
|---|---|
| `AlarmManager` | Android system service that schedules future events |
| `PendingIntent` | A "permission slip" that lets AlarmManager trigger your code later |
| `BroadcastReceiver` | Class that receives the alarm trigger — must be in Manifest |
| `setExact()` | Fires alarm at EXACTLY the time you specify |
| `RTC_WAKEUP` | Wakes the device if asleep to fire the alarm |
| Permission needed | `SCHEDULE_EXACT_ALARM` |

**Exam mod:** Change Toast to a Notification inside `AlarmReceiver` for better marks. Change seconds to `seconds * 60 * 1000` for minutes.

---

### Module 2 — Broadcast (`broadcast/`)
**What it does:** Button sends a custom broadcast. `BroadcastReceiverExample` listens for it and shows a Toast.

| Concept | Explanation |
|---|---|
| Action string | The "address" of the broadcast. Must match EXACTLY in `sendBroadcast()` and in `<intent-filter>` in Manifest |
| `sendBroadcast(intent)` | Fires the broadcast to all matching receivers |
| `onReceive()` | Called inside the receiver when the broadcast arrives |

**Exam mod:** Change `"com.example.CUSTOM_ACTION"` to whatever the question specifies. Add more `putExtra()` data fields.

---

### Module 3 — Calculator (`calculator/`)
**What it does:** Two number inputs + Add button. Result shown in TextView.

**Exam mod:** Extend with Sub/Mul/Div buttons using a `when()` block:
```kotlin
val result = when (v?.id) {
    R.id.btnAdd -> v1 + v2
    R.id.btnSub -> v1 - v2
    R.id.btnMul -> v1 * v2
    R.id.btnDiv -> if (v2 != 0.0) v1 / v2 else "Error: divide by zero"
    else -> 0.0
}
```

---

### Module 4 — Contacts (`contacts/`)
**What it does:** Reads device contacts using `ContentResolver`. Shows first 5 in a TextView.

| Concept | Explanation |
|---|---|
| `ContentResolver` | Android's way to query structured data (contacts, SMS, etc.) |
| `Cursor` | The result of a query — loop through it like a table |
| `DISPLAY_NAME` | Column name for the contact's name |
| `NUMBER` | Column name for the phone number |
| Permission needed | `READ_CONTACTS` (also request at runtime on Android 6+) |

**Exam mod:** Display in a `ListView` instead of `TextView`. Add a search filter.

---

### Module 5 — Converter (`converter/`)
**What it does:** Celsius input → Fahrenheit result on button click.

**Formulas:**
```
Fahrenheit = (Celsius × 9 / 5) + 32
Celsius    = (Fahrenheit − 32) × 5 / 9
```

**Exam mod:** Add a second EditText for Fahrenheit → Celsius. Add other units (km/miles, kg/lbs).

---

### Module 6 — Dialer (`dialer/`)
**What it does:** User types a number → button opens phone dialer with number pre-filled.

| Intent action | Behaviour | Permission needed |
|---|---|---|
| `Intent.ACTION_DIAL` | Opens dialer, user still taps Call | None |
| `Intent.ACTION_CALL` | CALLS immediately without confirmation | `CALL_PHONE` in Manifest + runtime |

**Exam mod:** Use `ACTION_CALL` for hospital/PS6 type questions. Add a hardcoded doctor list with a ListView.

---

### Module 7 — Email (`email/`)
**What it does:** To/Subject/Body inputs → opens device email app with fields pre-filled.

**Exam mod:** Hardcode the To address if the question specifies one:
```kotlin
val emailAddress = "principal@college.edu"  // hardcoded
```
Add CC/BCC with `Intent.EXTRA_CC` and `Intent.EXTRA_BCC`.

---

### Module 8 — Extras / Toast (`extras/`)
**What it does:** Demonstrates a Toast. Extend for Snackbar or TextWatcher.
```kotlin
// Toast
Toast.makeText(context, "Your message", Toast.LENGTH_SHORT).show()

// Snackbar (needs a View reference)
Snackbar.make(view, "Message", Snackbar.LENGTH_SHORT)
    .setAction("UNDO") { /* undo action */ }
    .show()
```

---

### Module 9 — Rating (`feedback/`)
**What it does:** 5-star RatingBar. Submit shows rating as Toast.

**Key attributes:**
- `android:numStars="5"` — number of stars
- `android:stepSize="1.0"` — whole stars only (`0.5` for half stars)
- `ratingBar.rating` — returns a `Float` (e.g. 3.0, 4.5)

**Exam mod:** Add different messages per rating level using a `when()` block.

---

### Module 10 — Grade (`grade/`)
**What it does:** Marks (0–100) input → Grade letter output.

**Exam mod:** Change boundaries and add descriptions:
```kotlin
val (grade, desc) = when {
    marks >= 90 -> Pair("S", "Outstanding")
    marks >= 80 -> Pair("A", "Excellent")
    marks >= 70 -> Pair("B", "Very Good")
    marks >= 60 -> Pair("C", "Good")
    marks >= 50 -> Pair("D", "Average")
    else        -> Pair("F", "Fail")
}
```

---

### Module 11 — ListView (`listview/`)
**What it does:** Array of strings displayed in a scrollable, clickable list.

| Concept | Explanation |
|---|---|
| `ArrayAdapter` | Bridges your data array to the ListView |
| `android.R.layout.simple_list_item_1` | Built-in single-line row layout (use this, don't create your own) |
| `setOnItemClickListener` | Fires when a row is tapped, gives you the position index |

**Exam mod:** Change the `items` array. Add search with `adapter.filter.filter(searchText)`.

---

### Module 12 — Service (`service/`)
**What it does:** Start/Stop buttons launch/kill a background `Service`.

| Concept | Explanation |
|---|---|
| `Service` | Runs in background even when user leaves the app |
| `START_STICKY` | If Android kills the service (low memory), restart it |
| `onBind() = null` | Unbound service — simpler, no connection to Activity needed |
| `onStartCommand()` | Called each time `startService()` is called — put your work here |
| `onDestroy()` | Called when `stopService()` is called — release resources here |

**Exam mod:** Add `MediaPlayer` for music. Put `.mp3` in `res/raw/`:
```kotlin
private var player: MediaPlayer? = null

override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    if (player == null) {
        player = MediaPlayer.create(this, R.raw.your_song)
        player?.isLooping = true
        player?.start()
    }
    return START_STICKY
}

override fun onDestroy() {
    player?.stop()
    player?.release()
    player = null
    super.onDestroy()
}
```

---

### Module 13 — SMS (`sms/`)
**What it does:** `SmsReceiver` intercepts incoming SMS and shows sender + body as a Toast.

| Concept | Explanation |
|---|---|
| `BroadcastReceiver` | Listens for the `SMS_RECEIVED` system action |
| `SmsMessage.createFromPdu()` | Converts raw binary SMS data into a readable object |
| `displayOriginatingAddress` | The sender's phone number |
| `displayMessageBody` | The text content of the SMS |
| Permissions needed | `RECEIVE_SMS` + `READ_SMS` in Manifest |

**Extract OTP from SMS body:**
```kotlin
val otp = Regex("\\d{4,6}").find(message)?.value ?: ""
etOtp.setText(otp)  // auto-fill the OTP EditText
```

---

## AndroidManifest Permissions Guide
```xml
<!-- ADD ONLY WHAT YOUR QUESTION NEEDS -->

<!-- Calling -->
<uses-permission android:name="android.permission.CALL_PHONE" />        <!-- ACTION_CALL only -->

<!-- SMS -->
<uses-permission android:name="android.permission.SEND_SMS" />           <!-- sending SMS programmatically -->
<uses-permission android:name="android.permission.RECEIVE_SMS" />        <!-- SmsReceiver -->
<uses-permission android:name="android.permission.READ_SMS" />           <!-- reading SMS content -->

<!-- Contacts -->
<uses-permission android:name="android.permission.READ_CONTACTS" />

<!-- Network -->
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />  <!-- no user approval needed -->

<!-- Notifications (required Android 13+) -->
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

<!-- Alarm (required Android 12+) -->
<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM" />

<!-- Location -->
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />    <!-- GPS precise -->
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />  <!-- WiFi approximate -->
```

### Registering Components
```xml
<!-- Every Activity -->
<activity android:name=".SecondActivity"/>

<!-- Every Service -->
<service android:name=".service.MyService"/>

<!-- BroadcastReceiver with custom action -->
<receiver android:name=".broadcast.BroadcastReceiverExample"
          android:exported="true">
    <intent-filter>
        <action android:name="com.example.CUSTOM_ACTION"/>
    </intent-filter>
</receiver>

<!-- SMS Receiver — system broadcast -->
<receiver android:name=".sms.SmsReceiver"
          android:exported="true">
    <intent-filter android:priority="999">
        <action android:name="android.provider.Telephony.SMS_RECEIVED"/>
    </intent-filter>
</receiver>

<!-- Alarm Receiver — no intent-filter needed -->
<receiver android:name=".alarm.AlarmReceiver"/>
```

> ⚠️ `android:exported="true"` is REQUIRED for any Receiver or Activity that receives intents from outside your app. Missing this causes a build error on API 31+.

---

## Android Components Explained

| Component | What it is | When to use it |
|---|---|---|
| **Activity** | A full screen with UI. Entry point via Manifest. | Every screen the user sees |
| **Fragment** | A piece of UI that lives inside an Activity. | Swapping content within one screen |
| **Service** | Background work with NO UI. | Music playback, downloads, location tracking |
| **BroadcastReceiver** | Listens for system or custom events. Very short-lived. | SMS received, alarm fired, boot completed |
| **Intent** | Messenger between components. Carries action + data. | Navigate screens, start services, send broadcasts |
| **PendingIntent** | A future Intent handed to another system to fire later. | AlarmManager, Notifications |

### Fragment Lifecycle (simple)
```
onCreateView     → inflate layout XML
onViewCreated    → do all findViewById and setOnClickListener HERE
onResume         → register BroadcastReceivers here
onPause          → unregister BroadcastReceivers here
onDestroyView    → clean up references
```

### Explicit vs Implicit Intent
```kotlin
// Explicit — you name the destination (navigate between your own screens)
val intent = Intent(this, SecondActivity::class.java)
startActivity(intent)

// Implicit — you describe what you want (Android picks the right app)
val intent = Intent(Intent.ACTION_DIAL)
intent.data = Uri.parse("tel:9876543210")
startActivity(intent)
```

---

## XML Layout Cheat Sheet

### Most Important Attributes

| Attribute | What it does |
|---|---|
| `android:id="@+id/myView"` | Unique ID — used in Kotlin with `findViewById` |
| `android:layout_width="match_parent"` | Fill full width of parent |
| `android:layout_width="wrap_content"` | Only as wide as the content |
| `android:layout_height="0dp"` + `android:layout_weight="1"` | Fill remaining space in LinearLayout |
| `android:orientation="vertical"` | Stack children top-to-bottom (LinearLayout) |
| `android:orientation="horizontal"` | Place children left-to-right (LinearLayout) |
| `android:gravity="center"` | Center content INSIDE the view |
| `android:layout_gravity="center"` | Center THIS view inside its parent |
| `android:padding="16dp"` | Inner spacing (border to content) |
| `android:layout_margin="8dp"` | Outer spacing (between this and other views) |
| `android:hint="Type here..."` | Grey placeholder in EditText |
| `android:textSize="18sp"` | Font size — always use `sp` (respects user font settings) |
| `android:inputType="number"` | Keyboard type for EditText |
| `android:visibility="gone"` | Hide and remove from layout (`visible` / `invisible` / `gone`) |

### inputType values

| Value | Keyboard shown |
|---|---|
| `text` | Normal keyboard (default) |
| `number` | Number only (integers) |
| `numberDecimal` | Number with decimal point |
| `phone` | Phone number keyboard |
| `textPassword` | Dots (hidden input) |
| `textEmailAddress` | @ key visible |
| `textMultiLine` | Enter key creates new lines |

### Widget Quick Reference
```xml
<!-- Spinner (dropdown) -->
<Spinner android:id="@+id/spProgramme"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"/>

<!-- RadioGroup (only one can be selected) -->
<RadioGroup android:id="@+id/rgGender"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal">
    <RadioButton android:id="@+id/rbMale"   android:text="Male"/>
    <RadioButton android:id="@+id/rbFemale" android:text="Female"/>
</RadioGroup>

<!-- CheckBox (multiple can be selected) -->
<CheckBox android:id="@+id/cbSports" android:text="Sports"/>
<CheckBox android:id="@+id/cbMusic"  android:text="Music"/>

<!-- DatePicker -->
<DatePicker android:id="@+id/dpDob"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:calendarViewShown="false"/>

<!-- RatingBar -->
<RatingBar android:id="@+id/ratingBar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:numStars="5"
    android:stepSize="1.0"/>

<!-- Horizontal divider line -->
<View android:layout_width="match_parent"
    android:layout_height="1dp"
    android:background="#000000"
    android:layout_marginTop="8dp"
    android:layout_marginBottom="8dp"/>
```

---

## Kotlin Patterns

### Validation — always do this before processing input
```kotlin
val input = editText.text.toString().trim()  // .trim() removes spaces

if (input.isEmpty()) {
    Toast.makeText(this, "Field is empty!", Toast.LENGTH_SHORT).show()
    return  // STOP here
}

// Safe number conversion
val number = input.toIntOrNull()
if (number == null || number < 0 || number > 100) {
    Toast.makeText(this, "Enter a valid number 0–100", Toast.LENGTH_SHORT).show()
    return
}
```

### Intent — navigate and pass data
```kotlin
// Send data
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra("KEY_NAME", "Arjun")   // String
intent.putExtra("KEY_AGE", 22)          // Int
startActivity(intent)

// Receive in SecondActivity
val name = intent.getStringExtra("KEY_NAME") ?: "Unknown"
val age  = intent.getIntExtra("KEY_AGE", 0)
tvDisplay.text = "Name: $name, Age: $age"
```

### Notification
```kotlin
// Step 1 — Create channel (call once in onCreate)
fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel("CH_ID", "My Alerts",
            NotificationManager.IMPORTANCE_DEFAULT)
        getSystemService(NotificationManager::class.java)
            .createNotificationChannel(channel)
    }
}

// Step 2 — Show notification
fun showNotification(title: String, message: String) {
    val n = NotificationCompat.Builder(this, "CH_ID")
        .setSmallIcon(android.R.drawable.ic_dialog_info)
        .setContentTitle(title)
        .setContentText(message)
        .setAutoCancel(true)
        .build()
    getSystemService(NotificationManager::class.java).notify(1, n)
}
```

### Spinner (dropdown) setup
```kotlin
val items = arrayOf("MCA", "MBA", "MSc", "MTech")
val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
spinner.adapter = adapter

// Get selected value:
val selected = spinner.selectedItem.toString()
```

### RadioGroup — get selected value
```kotlin
val selectedId = radioGroup.checkedRadioButtonId
if (selectedId == -1) {
    Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show()
    return
}
val selectedText = findViewById<RadioButton>(selectedId).text.toString()
```

### CheckBox — get selected values
```kotlin
var hobbies = ""
if (cbSports.isChecked) hobbies += "Sports "
if (cbMusic.isChecked)  hobbies += "Music "
if (cbReading.isChecked) hobbies += "Reading "

if (hobbies.isEmpty()) {
    Toast.makeText(this, "Select at least one hobby!", Toast.LENGTH_SHORT).show()
    return
}
```

### DatePicker — get selected date
```kotlin
val day   = datePicker.dayOfMonth
val month = datePicker.month + 1   // ⚠️ months are 0-indexed, always add +1
val year  = datePicker.year
val dob   = "$day/$month/$year"
```

### Options Menu (3-dot menu)
```kotlin
// 1. Create res/menu/main_menu.xml first
// 2. Add to Activity:

override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
        R.id.menuItem1 -> { /* do action */ true }
        R.id.menuItem2 -> { /* do action */ true }
        else -> super.onOptionsItemSelected(item)
    }
}
```

### Dark/Light Theme Toggle
```kotlin
var isDark = false

// Inside menu item handler:
isDark = !isDark
if (isDark) {
    window.decorView.setBackgroundColor(Color.BLACK)
    item.title = "Light Theme"
} else {
    window.decorView.setBackgroundColor(Color.WHITE)
    item.title = "Dark Theme"
}
```

### TextWatcher (live input listening)
```kotlin
editText.addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        tvLive.text = "You typed: ${s.toString()}"  // runs after every character
    }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
})
```

### Runtime Permission Request
```kotlin
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
    != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(this,
        arrayOf(Manifest.permission.CALL_PHONE), 100)
    return
}
// Permission already granted — proceed
makeCall()

// Handle the user's response
override fun onRequestPermissionsResult(requestCode: Int,
    permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == 100 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        makeCall()
    } else {
        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
    }
}
```

### WhatsApp Intent
```kotlin
val phone = etPhone.text.toString().trim()
val message = etMsg.text.toString().trim()
val wordCount = message.split(" ").filter { it.isNotBlank() }.size

if (phone.length != 10) { Toast.makeText(this, "Enter 10-digit number", Toast.LENGTH_SHORT).show(); return }
if (wordCount > 200) { Toast.makeText(this, "Max 200 words!", Toast.LENGTH_SHORT).show(); return }

val url = "https://api.whatsapp.com/send?phone=91" + phone + "&text=" + Uri.encode(message)
startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
```

---

## Suggested Improvements

### 1. Add Registration Form Module (PS5 type)
Create `registration/` package with `RegistrationFragment.kt` and `fragment_registration.xml`. Include: `EditText` (name, roll no), `Spinner` (programme), `RadioGroup` (gender), `CheckBox` (hobbies), `DatePicker` (DOB), `Button` (submit → next screen via Intent).

### 2. Extend MyService for Music Playback
Put an `.mp3` file in `res/raw/`. In `MyService.kt`:
```kotlin
private var player: MediaPlayer? = null

override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    if (player == null) {
        player = MediaPlayer.create(this, R.raw.your_song)
        player?.isLooping = true
        player?.start()
    }
    return START_STICKY
}

override fun onDestroy() {
    player?.stop()
    player?.release()
    player = null
    super.onDestroy()
}
```

### 3. Add Notification Module
Replace `AlarmReceiver`'s Toast with a proper `NotificationCompat` notification — it shows in the status bar and persists, giving better exam output marks.

### 4. Add Menu to MainActivity
Add `res/menu/main_menu.xml` and wire up `onCreateOptionsMenu` + `onOptionsItemSelected` in `MainActivity.kt` for Mock Test type questions (WhatsApp + Music + Dark Theme via menu).

### 5. Add a Validation Helper
Create `utils/ValidationUtils.kt` with reusable functions like `isNotEmpty()`, `isValidPhone()`, `isValidEmail()` that all fragments can call instead of repeating the same if-checks everywhere.

### 6. Add OTP Auto-fill to SmsReceiver
```kotlin
// In SmsReceiver.kt — extract OTP and broadcast it
val otp = Regex("\\d{4,6}").find(message)?.value ?: ""
val localIntent = Intent("OTP_RECEIVED")
localIntent.putExtra("OTP", otp)
context.sendBroadcast(localIntent)

// In your Activity — receive and auto-fill
val otpReceiver = object : BroadcastReceiver() {
    override fun onReceive(ctx: Context, intent: Intent) {
        etOtp.setText(intent.getStringExtra("OTP"))
    }
}
override fun onResume()  { registerReceiver(otpReceiver, IntentFilter("OTP_RECEIVED")) }
override fun onPause()   { unregisterReceiver(otpReceiver) }
```

---

## Common Errors and Fixes

| Error | Fix |
|---|---|
| **App crashes immediately on launch** | Check `AndroidManifest.xml`. The class name in `android:name` must exactly match your Kotlin class name. |
| **NullPointerException on `findViewById`** | The ID in `R.id.myId` must exactly match `android:id="@+id/myId"` in the XML. Check spelling. |
| **Gradle sync failed** | File → Invalidate Caches → Invalidate and Restart. Check internet connection. |
| **`Cannot resolve symbol 'R'`** | A broken XML file (even one missing `>` breaks everything). Look for red errors in layout XML. Try Build → Clean Project. |
| **Broadcast receiver not firing** | Action string in `sendBroadcast()` does NOT match `<action android:name="...">` in Manifest. They must be character-for-character identical. |
| **SMS not received in emulator** | Send SMS via emulator `...` panel → Phone tab. Check `RECEIVE_SMS` permission is in Manifest. |
| **Permission denied crash** | You used `ACTION_CALL` but forgot `CALL_PHONE` in Manifest, OR the runtime permission was denied — handle `onRequestPermissionsResult`. |
| **`android:exported` build error** | Add `android:exported="true"` to any `<receiver>` or `<activity>` that receives external intents. Required on API 31+. |
| **Notification not showing (Android 13+)** | Add `<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>` and request it at runtime. |
| **`setExact()` permission error** | Add `<uses-permission android:name="android.permission.SCHEDULE_EXACT_ALARM"/>` to Manifest. |
| **DatePicker month is one less** | Android months are 0-indexed (Jan=0, Dec=11). Always use `datePicker.month + 1`. |
| **Spinner shows blank first item** | Add a `"Select..."` prompt as first array item or call `spinner.setSelection(0)` after setting adapter. |
| **Service not stopping / music still playing** | Call `player?.stop()` and `player?.release()` inside `onDestroy()`. |
| **Copied code: wrong package** | Delete the pasted `package com.example.labexammasterapp` line. Keep your own project's package line. |
| **Emulator is very slow** | AVD Settings → increase RAM to 2048 MB + enable Hardware Acceleration. Or use a real device. |
| **`ClassNotFoundException` for Activity/Service/Receiver** | That component is missing from `AndroidManifest.xml`. Add the corresponding `<activity>`, `<service>`, or `<receiver>` tag. |

---

---

# 📖 EXAM QUICK REFERENCE GUIDE

---

## 🔍 KEYWORD SEARCH — What Question = What Folder

Read your exam question, find a word below, open that folder.

### Alarms & Notifications
| Question says... | Open this |
|---|---|
| Alarm / Reminder / Schedule / Timer | `alarm/AlarmFragment.kt` |
| Notification / Status bar alert | `notification/NotificationFragment.kt` |

### Communication
| Question says... | Open this |
|---|---|
| SMS / Text message / OTP / Auto-fill | `sms/SmsFragment.kt` + `SmsReceiver.kt` |
| SMS inline / Register receiver in screen | `inlinesms/InlineSmsFragment.kt` |
| Email / Send mail / Compose | `email/EmailFragment.kt` |
| WhatsApp / Send WhatsApp | `whatsapp/WhatsAppFragment.kt` |
| Broadcast / Send signal / Custom action | `broadcast/BroadcastFragment.kt` |

### Phone & Contacts
| Question says... | Open this |
|---|---|
| Dial / Call / Phone number / Open dialer | `dialer/DialerFragment.kt` |
| Contacts / Phone book / Read contacts | `contacts/ContactsFragment.kt` |
| Contacts + tap to call directly | `contactscall/ContactsCallFragment.kt` |
| Context menu / Long press menu | `contextmenu/ContextMenuFragment.kt` |

### UI & Layouts
| Question says... | Open this |
|---|---|
| Toast / Simple popup | `extras/ToastFragment.kt` |
| Snackbar / Long press / Touch / Drag | `snackbartouch/SnackbarTouchFragment.kt` |
| Rating / Stars / Feedback | `feedback/RatingFragment.kt` |
| List / ListView / Display items | `listview/ListViewFragment.kt` |
| Image toggle / Change image on click | `imagetoggle/ImageToggleFragment.kt` |
| Visiting card / Static layout | `visitingcard/VisitingCardFragment.kt` |

### Forms & Input
| Question says... | Open this |
|---|---|
| Registration form / Spinner / RadioButton / CheckBox / DatePicker | `registration/RegistrationFragment.kt` |
| TextWatcher / Live typing / Pass data to next screen | `textwatcher/TextWatcherFragment.kt` |

### Math & Logic
| Question says... | Open this |
|---|---|
| Calculator / Add / Subtract / Multiply / Divide | `calculator/CalculatorFragment.kt` |
| Grade / Marks / Pass or Fail | `grade/GradeFragment.kt` |
| Convert / Temperature / Celsius / Fahrenheit | `converter/TemperatureConverterFragment.kt` |

### Background & System
| Question says... | Open this |
|---|---|
| Service / Background / Music / Keep playing after exit | `service/ServiceFragment.kt` + `MyService.kt` |
| Menu / 3-dot menu / Dark theme / Light theme | `menu/MenuActivity.kt` |
| Internet check / USB debugging / Location on-off | `security/SecurityFragment.kt` |

---

## 🗺️ PROJECT MAP — Every File in Plain English
```
LabExamMasterApp/
│
├── MainActivity.kt
│       The home screen. Shows all buttons at the top.
│       Each button opens one module below.
│       Only edit this to add a new button.
│
├── alarm/
│       AlarmFragment.kt     → User types seconds → presses SET ALARM
│       AlarmReceiver.kt     → Runs when alarm fires → put your action here
│
├── broadcast/
│       BroadcastFragment.kt          → Button sends a broadcast message
│       BroadcastReceiverExample.kt   → Catches that broadcast → shows Toast
│
├── calculator/
│       CalculatorFragment.kt   → Two number inputs + four buttons (+  −  ×  ÷)
│
├── contacts/
│       ContactsFragment.kt    → Button loads first 5 contacts into a text box
│
├── contactscall/
│       ContactsCallFragment.kt  → Shows all contacts in a 2-line list
│                                   Tap any contact → calls them directly
│                                   ⚠ This is an Activity, not a Fragment
│
├── contextmenu/
│       ContextMenuFragment.kt  → Long press a phone number
│                                  Menu pops up: Call / SMS / WhatsApp
│                                  ⚠ This is an Activity, not a Fragment
│
├── converter/
│       TemperatureConverterFragment.kt   → Type Celsius → get Fahrenheit
│
├── dialer/
│       DialerFragment.kt   → Type a number → opens the phone dialer
│
├── email/
│       EmailFragment.kt   → Fill To / Subject / Body → opens email app
│
├── extras/
│       ToastFragment.kt   → Button shows a simple Toast popup
│
├── feedback/
│       RatingFragment.kt   → 5-star bar → submit shows the rating
│
├── grade/
│       GradeFragment.kt   → Type marks → shows grade letter
│
├── inlinesms/
│       InlineSmsFragment.kt   → Receives SMS without a separate receiver class
│                                 Shows sender and message live on screen
│
├── listview/
│       ListViewFragment.kt   → Scrollable list → tap item shows Toast
│
├── service/
│       MyService.kt        → Background service that plays music / ringtone
│       ServiceFragment.kt  → Two buttons: START SERVICE and STOP SERVICE
│
├── sms/
│       SmsFragment.kt    → Screen that shows incoming SMS live
│       SmsReceiver.kt    → Catches ALL incoming SMS even when app is closed
│
├── snackbartouch/
│       SnackbarTouchFragment.kt   → Snackbar demo + long press + touch drag events
│
└── textwatcher/
        TextWatcherFragment.kt       → Live typing preview + send 2 numbers to next screen
        TextWatcherResultActivity.kt → Second screen that shows the result
```

---

## 5 Steps to Use This in the Exam
```
Step 1 — Read the question. Underline the key action words.
          Example words: "call", "SMS", "service", "grade", "broadcast"

Step 2 — Find that word in the KEYWORD SEARCH table above.
          Note the folder and file name it points to.

Step 3 — Open that Kotlin file.
          Find the comment:  // ===== EXAM MODIFICATION AREA =====
          Change only what is inside that area.

Step 4 — Open the matching XML file if you need to change the screen design.
          It has the same name as the Kotlin file, just in  res/layout/

Step 5 — Run the app and test it.
```

---

## ✅ DO and ❌ DO NOT

### In Kotlin Code

| ✅ DO THIS | ❌ NOT THIS |
|---|---|
| `view.findViewById<>()` inside Fragments | `findViewById<>()` without `view.` in a Fragment |
| `requireContext()` when you need Context inside a Fragment | `this` as Context inside a Fragment |
| `context` for Toast inside a Fragment | `this` for Toast inside a Fragment |
| `this` as Context inside an Activity | `requireContext()` inside an Activity |
| Only change code inside `EXAM MODIFICATION AREA` | Randomly edit code outside that area |

### When Copy-Pasting Into a New Project

| ✅ DO THIS | ❌ NOT THIS |
|---|---|
| Keep your own `package com.yourname.yourapp` line | Copy the package line from this template |
| Delete `import com.example.labexammasterapp.R` | Copy that import into your new project |
| Copy the XML file with the exact same name | Rename the XML without updating the Kotlin |
| Copy the `<uses-permission>` tags you need | Forget to add permissions to your new Manifest |
| Register every Activity, Service, Receiver in Manifest | Forget the Manifest registration |

### Converting Fragment to Activity (for most exam questions)

| ✅ DO THIS | ❌ NOT THIS |
|---|---|
| Change `: Fragment(R.layout.xxx)` to `: AppCompatActivity()` | Forget to change the class it extends |
| Change `onViewCreated` to `onCreate` | Leave `onViewCreated` in an Activity |
| Add `super.onCreate()` and `setContentView()` at top | Forget those two lines |
| Remove all `view.` from `view.findViewById(...)` | Leave `view.` prefixes in an Activity |
| Change all `requireContext()` to `this` | Leave `requireContext()` in an Activity |

### In XML Layouts

| ✅ DO THIS | ❌ NOT THIS |
|---|---|
| Use `sp` for text size: `android:textSize="18sp"` | Use `dp` for text size |
| Use `dp` for margins and padding | Use `px` anywhere |
| Give every view a unique `android:id` | Use the same ID twice in one layout |
| Add `android:hint="..."` to every EditText | Leave EditText with no hint |
| Use `android:inputType="number"` for number fields | Leave inputType blank on number fields |

### In AndroidManifest.xml

| ✅ DO THIS | ❌ NOT THIS |
|---|---|
| Register every Activity: `<activity android:name=".YourActivity"/>` | Leave any Activity unregistered |
| Register every Service: `<service android:name=".YourService"/>` | Leave any Service unregistered |
| Add `android:exported="true"` on receivers that get system broadcasts | Forget it — causes build error on Android 12+ |
| Add `<uses-feature android:name="android.hardware.telephony" android:required="false"/>` for SMS | Set `required="true"` — blocks install on non-phone devices |

---

## 🔑 Key Android Words — One Line Each

| Word | What it means |
|---|---|
| **Activity** | A full screen with its own layout. Must be in Manifest. |
| **Fragment** | A piece of a screen that lives inside an Activity. |
| **Service** | Runs in the background with no visible screen. Use for music. |
| **BroadcastReceiver** | Listens for events (SMS arrived, alarm fired). No UI. |
| **Intent** | A message that opens Activities, starts Services, or sends Broadcasts. |
| **PendingIntent** | A future Intent handed to the system to fire later. Used for alarms. |
| **ContentResolver** | Reads Android's built-in databases (contacts, SMS, calendar). |
| **Cursor** | The result of a ContentResolver query. Loop through it like a spreadsheet. |
| **ArrayAdapter** | Connects a text array to a ListView. |
| **SimpleCursorAdapter** | Connects a database Cursor to a ListView. Shows 2 lines per row. |
| **Toast** | A small popup. No buttons. Disappears on its own. |
| **Snackbar** | A bar at the bottom. Can have an action button like UNDO. |
| **TextWatcher** | Fires on every single keystroke inside an EditText. |
| **AlarmManager** | The Android system service that runs code at a future time. |
| **MediaPlayer** | Plays audio files. Use inside a Service so it keeps playing. |
| **START_STICKY** | Tells Android to restart your Service automatically if it gets killed. |
| **onResume / onPause** | Register receivers in `onResume`. Always unregister in `onPause`. |
| **requireContext()** | The safe way to get a Context reference inside a Fragment. |
| **registerForContextMenu(view)** | Makes a view respond to long press with a menu. |

---

## 🚨 Top 10 Mistakes and How to Fix Them

**1. App crashes the moment it opens**
The class name in Manifest does not match the actual Kotlin class name.
Go to AndroidManifest.xml and check every `android:name=".XxxClass"`.
It must match exactly, letter for letter, including package.

---

**2. Screen is blank after fragment loads**
You used `findViewById` without `view.` inside a Fragment.
Inside a Fragment it is always `view.findViewById<Type>(R.id.yourId)`.

---

**3. Contacts list is empty even though permission is in Manifest**
Manifest permission alone is not enough on Android 6 and above.
You must also ask the user at runtime using `ActivityCompat.requestPermissions()`.
Copy the pattern from `ContactsFragment.kt` — it already handles this correctly.

---

**4. SMS never arrives in the app**
Two possible causes:
- The action string in `sendBroadcast()` does not exactly match the `<action>` in Manifest.
- You are testing wrong. On the emulator click `...` on the side panel → Phone tab → Send Message.

---

**5. Service starts but no music plays**
`MyService.kt` in this project already has `MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)`.
If your copy does not, add that line inside `onStartCommand`.

---

**6. Pasted code and now it will not compile**
You copied the `package com.example.labexammasterapp` line.
Delete it. Keep only your own project's package line at the very top.

---

**7. Red error: Cannot resolve symbol R**
One of your XML files has a syntax error. Even one missing `>` breaks everything.
Look at every XML file in Android Studio for red underlines.
Fix the error then go to Build → Clean Project → Rebuild Project.

---

**8. WhatsApp button does nothing on newer phones**
You are missing the `<queries>` block in Manifest.
Add this just before the closing `</manifest>` tag:
```xml
<queries>
    <package android:name="com.whatsapp"/>
    <package android:name="com.whatsapp.w4b"/>
</queries>
```

---

**9. DatePicker shows the wrong month**
Android months start from 0. January = 0, December = 11.
Always add 1 when you read it: `datePicker.month + 1`

---

**10. Copied Fragment but Activity won't start**
If you turned a Fragment into an Activity (for your exam project), you must also register it in Manifest.
Add `<activity android:name=".YourClassName"/>` inside the `<application>` block.

---

## ✔️ Before You Submit — Quick Checklist
```
□  App opens without crashing
□  The main feature works and shows a result on screen
□  If two screens needed — data passes correctly to screen 2
□  Permission dialog appears on first run (for call / SMS / contacts)
□  Service starts and stops without crashing
□  All EditTexts show hint text and have correct inputType
□  No red errors visible in Android Studio
□  Manifest has all permissions, activities, services, and receivers
```

---

*Paste everything above this line at the bottom of your existing README.md*