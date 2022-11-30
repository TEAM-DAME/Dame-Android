package com.yangbong.damedame.notification


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.damedame.notification.data.EmotionType
import com.yangbong.damedame.notification.data.NotificationData
import com.yangbong.damedame.notification.data.NotificationType
import com.yangbong.domain.entity.NotificationInfo
import com.yangbong.domain.entity.request.DomainNotificationRequest
import com.yangbong.domain.repository.NotificationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notificationRepository: NotificationRepository
) : BaseViewModel() {
    private val _notifications = MutableLiveData<List<NotificationData>>()
    val notifications: LiveData<List<NotificationData>>
        get() = _notifications

    suspend fun getNotifications() {
        viewModelScope.launch {
            val response = notificationRepository.getNotification(
                DomainNotificationRequest(
                    notificationRepository.getUserId()
                )
            )

            _notifications.value = response.getOrNull()?.notifications?.map { notificationInfo ->
                NotificationData(
                    notificationInfo = notificationInfo,
                    notificationMsg = generateNotificationMsg(notificationInfo)
                )
            }
        }
    }

    suspend fun deleteNotification() {
        viewModelScope.launch {
            notificationRepository.deleteNotification(
                DomainNotificationRequest(
                    notificationRepository.getUserId()
                )
            ).onSuccess {
                _notifications.value = listOf()
            }.onFailure {
                Timber.e(it)
            }
        }
    }

    private fun generateNotificationMsg(notificationInfo: NotificationInfo): String{
        return if (notificationInfo.notificationType == NotificationType.REQUEST_FRIEND.notificationTypeNumber) {
            "%s님이 친구 요청을 보냈습니다.".format(notificationInfo.userInfo.profileId)
        } else {
            "오늘의 감정은 %s입니다.".format(EmotionType.values().find { emotionType ->
                emotionType.emotionTypeNumber == notificationInfo.todayEmotion
            })
        }
    }
}