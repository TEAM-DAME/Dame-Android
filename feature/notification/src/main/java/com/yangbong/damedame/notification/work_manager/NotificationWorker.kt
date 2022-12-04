package com.example.testworkmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.work.*
import com.yangbong.damedame.notification.util.getWaitingTimeBeforeNotification
import java.util.concurrent.TimeUnit

class NotificationWorker(
    appContext: Context,
    parameters: WorkerParameters,
) : CoroutineWorker(appContext, parameters) {

    private val channelID = "DAME_DAME"
    private var notificationManager: NotificationManager? = null

    companion object {
        var notificationHour = 22
        var notificationMinute = 0
        const val WORK_NAME = "NOTIFICATION_WORK"
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
        val notificationId = 45

        val intent = Intent(applicationContext, this::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("Fragment", "HomeFragment")
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification: Notification = NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle("다메다메")
            .setContentText("오늘의 일기는 작성하셨나요?")
            .setSmallIcon(
                applicationContext.resources.getIdentifier(
                    "notification_icon.png",
                    "drawable",
                    applicationContext.packageName
                )
            )
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setWhen(System.currentTimeMillis())
            .build()

        notificationManager?.notify(notificationId, notification)
        return notification
    }

    private fun createNotificationChannel() {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelID, "DameDame", importance).apply {
            description = "Request writing diary"
        }
        notificationManager?.createNotificationChannel(channel)
    }

}