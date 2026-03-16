package com.example.labexammasterapp.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast

class MyService : Service() {

    private var player: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (player == null) {
            // ===== EXAM MODIFICATION AREA =====
            // Option 1 (default): plays the device ringtone — no extra files needed
            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
            // Option 2: play your own mp3 — add file to res/raw/ and replace 'your_song'
            // player = MediaPlayer.create(this, R.raw.your_song)
            // ==================================
            player?.isLooping = true
            player?.start()
        }
        Toast.makeText(this, "Service Started - Music Playing", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.stop()
        player?.release()
        player = null
        Toast.makeText(this, "Service Stopped - Music Stopped", Toast.LENGTH_SHORT).show()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
