package com.yangbong.data.repository

import com.yangbong.core_data.exception.RetrofitFailureStateException
import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.data_source.RemoteFriendsDataSource
import com.yangbong.data.remote.mapper.FriendMapper
import com.yangbong.domain.entity.FriendProfileInfo
import com.yangbong.domain.repository.FriendsRepository
import timber.log.Timber
import java.security.cert.CertificateException
import javax.inject.Inject

class FriendsRepositoryImpl @Inject constructor(
    private val remoteFriendsDataSource: RemoteFriendsDataSource,
    private val friendMapper: FriendMapper
) : FriendsRepository {
    override suspend fun getFriendList(
        userId: Int,
        page: Int,
        size: Int
    ): Result<List<FriendProfileInfo>> {
        when (val friendList = remoteFriendsDataSource.getFriendList(userId, page, size)) {
            is NetworkState.Success -> return Result.success(
                friendList.body.data.results.map {
                    friendMapper.toFriendInfo(it)
                }
            )
            is NetworkState.Failure -> {
                if (friendList.code == 401) {
                    throw CertificateException("토큰 만료 오류")
                } else {
                    return Result.failure(
                        RetrofitFailureStateException(
                            friendList.error,
                            friendList.code
                        )
                    )
                }
            }
            is NetworkState.NetworkError -> Timber.tag("${this.javaClass.name}_getFriendList")
                .d(friendList.error)
            is NetworkState.UnknownError -> Timber.tag("${this.javaClass.name}_getFriendList")
                .d(friendList.t)
        }
        return Result.failure(IllegalStateException("NetworkError or UnKnownError please check timber"))
    }
}