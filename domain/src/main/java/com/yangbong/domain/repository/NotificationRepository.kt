package com.yangbong.domain.repository

import com.yangbong.domain.entity.response.DomainNotificationResponse

interface NotificationRepository {
    suspend fun getMyProfileInfo(): Result<DomainNotificationResponse>
}