package com.yangbong.damedame.notification.work_manager

import java.util.*

class TimeCalculator {

    /**
     * 10시에 알람을 울리기 위해서 현재시간부터 10시까지의 시간차이를 구하는 함수
     */
    fun getWaitingTimeBeforeNotification(notificationHour: Int = 22): Long {

        val currentDate = Calendar.getInstance()
        val notificationDate = Calendar.getInstance()

        notificationDate.set(Calendar.HOUR_OF_DAY, notificationHour)
        notificationDate.set(Calendar.MINUTE, 0)
        notificationDate.set(Calendar.SECOND, 0)

        if (notificationDate.before(currentDate)) {
            notificationDate.add(Calendar.HOUR_OF_DAY, 24)
        }

        return notificationDate.timeInMillis - currentDate.timeInMillis
    }
}