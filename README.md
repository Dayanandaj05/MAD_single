# LabExamMasterApp 🎓 - Android Lab Survival Template

Welcome to the **Universal Android Lab Exam Template**. This project is designed specifically for Android lab exams. It contains simple examples for the most common exam questions.

## How to use this project during an exam

1. **Open** this project in Android Studio.
2. **Find** the feature that matches your exam question using the keyword guide below.
3. **Open** the specific `Fragment.kt` and `layout.xml` file for that feature.
4. **Modify** the simple parts marked with `// ===== EXAM MODIFICATION AREA =====`.
5. Run the app!

If the question says "Dial a phone number":
* Open: `dialer/DialerFragment.kt` and `res/layout/fragment_dialer.xml`
* Modify: `editPhone` hint text, or button text.

## EXAM KEYWORD GUIDE

Use this guide to find the correct folder based on your exam question:

* **Alarm / Notification** → `alarm` folder
* **Dial / Phone** → `dialer` folder
* **Grade / Marks** → `grade` folder
* **Broadcast** → `broadcast` folder
* **SMS** → `sms` folder
* **Service** → `service` folder
* **Email** → `email` folder
* **Contacts** → `contacts` folder
* **ListView** → `listview` folder
* **Converter** → `converter` folder
* **Calculator** → `calculator` folder
* **RatingBar** → `feedback` folder
* **TextWatcher, Snackbar, Data Passing, etc** → `extras` folder

## CRASH FIX GUIDE & TROUBLESHOOTING

Common problems:
* **App crashes when button clicked** → wrong ID name in `findViewById` matching XML.
* **Permission error** → missing permission in `AndroidManifest.xml`.
* **Broadcast not working** → action name mismatch between sender and receiver.
* **App isn't opening (Manifest Error)** → Your `AndroidManifest.xml` might be looking for a class named `MainActivity`, but you pasted a Fragment code (e.g. `GradeFragment`).
   **Fix:**
   1. Rename the pasted class from `GradeFragment` to `MainActivity`.
   2. Change it to inherit from `AppCompatActivity` instead of `Fragment` (e.g. `class MainActivity : AppCompatActivity()`).
   3. Move your code into `onCreate` and use `setContentView(R.layout.activity_main)` (or your layout file) instead of `onViewCreated`.
   4. Update `findViewById` correctly for an Activity by removing the `view.` prefix.

## ⚠️ IMPORTANT: HOW TO COPY-PASTE INTO ANDROID STUDIO

If your teacher requires you to create a **brand new** Empty Views Activity project in Android Studio, follow these rules:

1. **NEVER COPY THE `package` LINE**: 
   Leave your project's top line `package com.yourname.yourapp` untouched.
2. **NEVER COPY `import com.example.labexammasterapp.R`**: 
   Android Studio will automatically import your own `R` file.
3. **XML LAYOUT NAMES MATCH**:
   If you copy `fragment_dialer.xml`, make sure you create a file named `fragment_dialer.xml` in `res/layout` inside your new project.
4. **MANIFEST PERMISSIONS**:
   Don't forget to copy the `<uses-permission ...>` tags to your new `AndroidManifest.xml`.
