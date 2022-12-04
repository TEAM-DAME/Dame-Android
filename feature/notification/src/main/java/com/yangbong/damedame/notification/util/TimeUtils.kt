package com.yangbong.damedame.notification.util

import java.util.*

/**
 * 10시에 알람을 울리기 위해서 현재시간부터 10시까지의 시간차이를 구하는 함수
 */
import android.content.ContentValues.TAG
import android.util.Log

fun getWaitingTimeBeforeNotification(notificationHour: Int, notificationMinute: Int): Long {
    val notificationDate = Calendar.getInstance()
    val currentDate = Calendar.getInstance()

    notificationDate.set(Calendar.HOUR_OF_DAY, notificationHour)
    notificationDate.set(Calendar.MINUTE, notificationMinute)
    notificationDate.set(Calendar.SECOND, 0)
    Log.i(TAG, "hour: ${notificationHour}\n")

    Log.i(TAG, "minute: ${notificationMinute}\n")

    Log.i(TAG, "current hour v1: ${Calendar.HOUR_OF_DAY}\n")
    Log.i(TAG, "current minute: ${Calendar.MINUTE}\n")
    if (notificationDate.timeInMillis < currentDate.timeInMillis) {
        notificationDate.add(Calendar.HOUR_OF_DAY, 24)
    }

    Log.i(TAG, "duration: ${notificationDate.timeInMillis - currentDate.timeInMillis}\n")
    return notificationDate.timeInMillis - currentDate.timeInMillis
}
