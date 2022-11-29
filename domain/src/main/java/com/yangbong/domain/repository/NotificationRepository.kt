package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainNotificationRequest
import com.yangbong.domain.entity.response.DomainNotificationResponse

interface NotificationRepository {

    fun getUserId(): Int

    suspend fun getNotification(
        domainNotificationRequest: DomainNotificationRequest
    ): Result<DomainNotificationResponse>

    suspend fun deleteNotification(
        domainNotificationRequest: DomainNotificationRequest
    ): Result<String>
}