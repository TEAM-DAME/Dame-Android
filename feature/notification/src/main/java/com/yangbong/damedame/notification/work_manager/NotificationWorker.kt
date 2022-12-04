package com.example.testworkmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.yangbong.damedame.notification.SettingActivity
import com.yangbong.damedame.notification.util.getWaitingTimeBeforeNotification
import java.util.concurrent.TimeUnit

class NotificationWorker(
    appContext: Context,
    parameters: WorkerParameters,
) : CoroutineWorker(appContext, parameters) {

    private lateinit var notificationManager: NotificationManager

    companion object {
        var notificationHour = 22
        var notificationMinute = 0

        const val NOTIFICATION_ID = 45
        const val WORK_NAME = "NOTIFICATION_WORK"
        const val CHANNEL_ID = "DAME_DAME"
        const val CHANNEL_NAME = "DAME_DAME_CHANNEL"
    }

    override suspend fun doWork(): Result {

        try {
            notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            createNotificationChannel()
            displayNotification()

            val oneTimeWorkRequest =
                OneTimeWorkRequestBuilder<NotificationWorker>().setInitialDelay(
                    getWaitingTimeBeforeNotification(notificationHour, notificationMinute),
                    TimeUnit.MILLISECONDS
                ).build()

            WorkManager.getInstance(applicationContext)
                .enqueueUniqueWork(WORK_NAME, ExistingWorkPolicy.REPLACE, oneTimeWorkRequest)

        } catch (e: Exception) {
            Result.retry()
        }
        return Result.success()
    }

    private fun displayNotification(): Notification {
        val intent = Intent(applicationContext, SettingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            putExtra("Fragment", "HomeFragment")
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification: Notification =
            NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setContentTitle(applicationContext.resources.getString(com.yangbong.damedame.notification.R.string.dame_dame))
                .setContentText(applicationContext.resources.getString(com.yangbong.damedame.notification.R.string.writing_diary_msg))
                .setSmallIcon(com.yangbong.damedame.notification.R.drawable.notification_icon)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
        return notification
    }

    private fun createNotificationChannel() {

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
    }

}