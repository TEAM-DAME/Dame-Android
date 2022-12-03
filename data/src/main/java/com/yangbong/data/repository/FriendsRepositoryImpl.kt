package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteUserProfileDataSource
import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.repository.FriendsRepository
import timber.log.Timber
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(
    private val remoteUserProfileDataSource: RemoteUserProfileDataSource
):FriendsRepository{
    override suspend fun getUserProfileInfo(userId:Int): Result<ProfileInfo> {
        when(val response=remoteUserProfileDataSource.getUserProfile(userId)){
            is NetworkState.Success->return Result.success(
                ProfileInfo(
                    nickName = response.body.data.nickName,
                    profileImageUrl = response.body.data.profileImageUrl,
                    diaryCount = response.body.data.diaryCount,
                    minionCount = response.body.data.minionCount,
                    friendCount = response.body.data.friendCount,
                    isFriend = response.body.data.isFriend
                )
            )
            is NetworkState.Failure->
                return Result.failure(
                    RetrofitFailureStateException(
                        response.error,
                        response.code
                    )
                )
            is NetworkState.NetworkError -> Timber.d(response.error)
            is NetworkState.UnknownError -> Timber.d(response.t)

        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}