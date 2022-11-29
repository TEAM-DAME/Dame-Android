package com.yangbong.data.repository

import android.net.Network
import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteNotificationDataSource
import com.yangbong.data.remote.data_source.RemoteSetCharacterDataSource
import com.yangbong.data.remote.mapper.NotificationMapper
import com.yangbong.data.remote.model.request.NotificationRequest
import com.yangbong.domain.entity.request.DomainNotificationRequest
import com.yangbong.domain.entity.response.DomainNotificationResponse
import com.yangbong.domain.repository.NotificationRepository
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val notificationDataSource: RemoteNotificationDataSource,
    private val notificationMapper: NotificationMapper
) : NotificationRepository {

    override fun getUserId(): Int = localPreferenceUserDataSource.getUserId()


    override suspend fun getNotification(
        domainNotificationRequest: DomainNotificationRequest
    ): Result<DomainNotificationResponse> {

        val response = notificationDataSource.getNotification(
            NotificationRequest(
                userId = domainNotificationRequest.userId
            )
        )

        when (response) {
            is NetworkState.Success ->
                return Result.success(
                    DomainNotificationResponse(
                        notifications = response.body.data.notificationData.map {
                            notificationMapper.toNotificationInfo(it)
                        }
                    )
                )
            is NetworkState.Failure ->
                return Result.failure(
                    RetrofitFailureStateException(
                        response.error,
                        response.code
                    )
                )
            else ->
                return Result.failure(
                    IllegalStateException(
                        "UnKnownError please check "
                    )
                )
        }

    }

    override suspend fun deleteNotification(
        domainNotificationRequest: DomainNotificationRequest
    ): Result<String> {
        val response = notificationDataSource.deleteNotification(
            NotificationRequest(
                userId = domainNotificationRequest.userId
            )
        )

        when (response) {
            is NetworkState.Success ->
                return Result.success(
                    response.body.message
                )
            is NetworkState.Failure ->
                return Result.failure(
                    RetrofitFailureStateException(
                        response.error,
                        response.code
                    )
                )
            else ->
                return Result.failure(
                    IllegalStateException(
                        "UnKnownError please check "
                    )
                )
        }
    }
}