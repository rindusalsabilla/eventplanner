package id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import id.ac.ui.cs.mobileprogramming.rindusalsabilla.eventplanner.ui.event.AddEventActivity.Companion.NOTIFICATION_CHANNEL_ID

class NotificationPublisher : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification: Notification? = intent?.getParcelableExtra(NOTIFICATION)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val id = intent?.getIntExtra(NOTIFICATION_ID, 0)

        if (id != null) {
            notificationManager.notify(id, notification)
        } else {
            Toast.makeText(context, "Notification ID Null", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        var NOTIFICATION_ID = "notification-id"
        var NOTIFICATION = "notification"
    }
}