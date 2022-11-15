package com.yangbong.damedame

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DameDameMessagingService : FirebaseMessagingService() {

    @Override
    override fun onNewToken(token: String) {
        // 서버에게 토큰 새로 보내주는 코드
    }

    @Override
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // TODO(채널 생성 등 로직 추가)
    }

    /**
     * notification을 생성하는 함수
     */
    private fun makeNotificationChannel(
        channelId: String,
        channelName: String,
        notificationId: Int,
        title: String,
        text: String,
        icon: Int
    ) {
        val notificationManager =
            NotificationManagerCompat.from(applicationContext)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            notificationManager.createNotificationChannel(channel)
            notificationManager.notify(
                notificationId,
                NotificationCompat.Builder(applicationContext, channelId)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setSmallIcon(icon)
                    .build()
            )
        }
    }
}
