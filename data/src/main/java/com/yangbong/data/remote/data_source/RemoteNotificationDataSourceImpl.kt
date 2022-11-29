package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.NotificationRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.model.response.NotificationResponse
import com.yangbong.data.remote.service.NotificationService
import javax.inject.Inject

class RemoteNotificationDataSourceImpl @Inject constructor(
    private val notificationService: NotificationService
) : RemoteNotificationDataSource {

    override suspend fun getNotification(notificationRequest: NotificationRequest): NetworkState<BaseResponse<NotificationResponse>> =
        notificationService.getNotification(notificationRequest.userId)

    override suspend fun deleteNotification(notificationRequest: NotificationRequest): NetworkState<EmptyResponse> =
        notificationService.deleteNotification(notificationRequest.userId)

}