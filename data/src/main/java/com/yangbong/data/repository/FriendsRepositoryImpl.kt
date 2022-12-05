package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteFriendListDataSource
import com.yangbong.data.remote.data_source.RemoteUserProfileDataSource
import com.yangbong.data.remote.mapper.SearchMapper
import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.entity.SearchInfo
import com.yangbong.domain.entity.response.DomainFriendListResponse
import com.yangbong.domain.repository.FriendsRepository
import timber.log.Timber
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(
    private val remoteUserProfileDataSource: RemoteUserProfileDataSource,
    private val remoteFriendListDataSource: RemoteFriendListDataSource,
    private val localPreferenceUserDataSource: LocalPreferenceUserDataSource,
    private val searchMapper: SearchMapper
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

    override suspend fun getFriendList(userId:Int,page:Int,size:Int): Result<DomainFriendListResponse> {
        val friendResult=remoteFriendListDataSource.getFriendList(userId, page, size)
        when(friendResult){
            is NetworkState.Success ->return Result.success(
                DomainFriendListResponse(
                    next=friendResult.body.info.next,
                    prev=friendResult.body.info.prev,
                    results= friendResult.body.results.map{
                        searchMapper.toSearchInfo(it)
                    } as ArrayList<SearchInfo>
                )
            )
            is NetworkState.Failure->
                return Result.failure(
                    RetrofitFailureStateException(
                        friendResult.error,
                        friendResult.code
                    )
                )
            is NetworkState.NetworkError -> Timber.d(friendResult.error)
            is NetworkState.UnknownError -> Timber.d(friendResult.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))

    }

    override fun getUserId(): Int {
        return localPreferenceUserDataSource.getUserId()
    }

    override fun getUserNickName(): String {
        return localPreferenceUserDataSource.getUserProfileNickname()
    }
}