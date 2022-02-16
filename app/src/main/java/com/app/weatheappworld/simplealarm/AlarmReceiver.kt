package com.app.weatheappworld.simplealarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.app.weatheappworld.R
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        //create a date reference
        val calendar: Calendar = Calendar.getInstance()
        val formatDate: SimpleDateFormat = SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.CANADA)
        val formatTime: SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.CANADA)
        val date = formatDate.format(calendar.time)
        val time = formatTime.format(calendar.time)

        // create a snooze intent
        val snoozeIntent = Intent(context, AlarmReceiver::class.java)
        snoozeIntent.putExtra(Notification.EXTRA_NOTIFICATION_ID, 0)
        val snoozePendingIntent = PendingIntent.getBroadcast(context, 0, snoozeIntent, 0)

        // create a notification
        val channelId = "1"
        val builder = NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.back)
                .setContentTitle("It is clear")
                .setContentText("$date  $time")
                .addAction(R.drawable.back, "SNOOZE", snoozePendingIntent)

        // create the intent for lunch the notification view
        val notificationIntent = Intent(context, AlarmReceiver::class.java)
        val contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(contentIntent)

        // add the notification
        val manager = (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
        manager.notify(0, builder.build())

        val managerCompat = NotificationManagerCompat.from(context)
        managerCompat.notify(1, builder.build())
        val notification = builder.build()
//        ShortcutBadger.applyCount(applicationContext, 1)
//        ShortcutBadger.applyNotification(applicationContext, notification, 1)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel("1", "aya3", importance)
            notificationChannel.enableLights(true)
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            assert(managerCompat != null)
            builder.setSmallIcon(R.mipmap.ic_launcher)
//            builder.setLargeIcon(img)
//            builder.setStyle(NotificationCompat.BigPictureStyle().bigLargeIcon(img))
//            builder.setSmallIcon(R.drawable.app_logo_yellow)
//            builder.color = resources.getColor(R.color.yellow3)

            builder.setChannelId("1")
            managerCompat.createNotificationChannel(notificationChannel)
            managerCompat.notify(1, builder.build())
        }




//        //add a alarm tone
//        val mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.alarm)
//        mediaPlayer.isLooping = true
//        mediaPlayer.start()
    }
}