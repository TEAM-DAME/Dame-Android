package com.yangbong.damedame.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.yangbong.core_ui.util.Injector
import hilt_aggregated_deps._com_yangbong_core_ui_util_Injector_MainNavigatorInjector

class NotificationGenerator(
    private val applicationContext: Context,
) {

    private val notificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
    }


    fun displayNotification(): Notification {

        val intent: Intent = NavigatorIntentObject.getMainIntent()
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification: Notification =
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setContentTitle(applicationContext.resources.getString(R.string.dame_dame))
                .setContentText(applicationContext.resources.getString(R.string.writing_diary_msg))
                .setSmallIcon(R.drawable.notification_icon)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        return notification
    }

    fun createNotificationChannel() {

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
    }

    companion object {
        const val NOTIFICATION_ID = 45
        const val CHANNEL_ID = "DAME_DAME"
        const val CHANNEL_NAME = "DAME_DAME_CHANNEL"
    }
}