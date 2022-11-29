package com.yangbong.data.remote.mapper

import com.yangbong.data.remote.model.response.Notification
import com.yangbong.domain.entity.NotificationInfo
import com.yangbong.domain.entity.UserInfo

class NotificationMapper {

    fun toNotificationInfo(notification: Notification): NotificationInfo {

        return NotificationInfo(
            notificationType = notification.notificationType,
            todayEmotion = notification.todayEmotion,
            userInfo = UserInfo(
                profileId = notification.profileId,
                profileImageUrl = notification.profileImageUrl
            )
        )
    }

}