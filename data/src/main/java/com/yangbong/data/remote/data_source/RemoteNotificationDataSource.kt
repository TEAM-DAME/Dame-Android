package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.NotificationRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.EmptyResponse
import com.yangbong.data.remote.model.response.NotificationResponse

interface RemoteNotificationDataSource {

    suspend fun getNotification(notificationRequest: NotificationRequest) :
            NetworkState<BaseResponse<NotificationResponse>>

    suspend fun deleteNotification(notificationRequest: NotificationRequest) :
            NetworkState<EmptyResponse>

}