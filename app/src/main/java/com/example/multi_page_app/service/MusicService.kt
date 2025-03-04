package com.example.multi_page_app.service

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.multi_page_app.Fragments.ForgroundServiceFragment

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private val CHANNEL_ID = "MusicServiceChannel"

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "START" -> startMusic()
            "PAUSE" -> pauseMusic()
            "STOP" -> stopMusic()
        }
        return START_STICKY
    }

    private fun startMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer().apply {
                val assetFileDescriptor = assets.openFd("RickAstley-NeverGonnaGiveYouUp.mp3")
                setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                prepare()
            }
        }

        if (!mediaPlayer!!.isPlaying) {
            mediaPlayer!!.start()
            startForeground(1, buildNotification("Playing Music"))
        }
    }

    private fun pauseMusic() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            startForeground(1, buildNotification("Music Paused"))
        }
    }

    private fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        stopForeground(STOP_FOREGROUND_REMOVE)
        stopSelf()
    }

    private fun buildNotification(contentText: String): Notification {
        val intent = Intent(this, ForgroundServiceFragment::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Music Player")
            .setContentText(contentText)
            .setContentIntent(pendingIntent)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Music Player Service",
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(serviceChannel)
        }
    }

    override fun onBind(intent: Intent?): IBinder? { return null}

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}
