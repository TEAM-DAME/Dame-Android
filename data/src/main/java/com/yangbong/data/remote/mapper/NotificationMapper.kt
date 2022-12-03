package com.yangbong.data.remote.mapper

import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSourceImpl
import com.yangbong.data.remote.model.response.Notification
import com.yangbong.domain.entity.NotificationInfo
import com.yangbong.domain.entity.UserInfo
import javax.inject.Inject

class NotificationMapper  @Inject constructor(
   val localPreferenceUserDataSource: LocalPreferenceUserDataSource
){

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