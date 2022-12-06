package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.FriendListResponse
import com.yangbong.data.remote.service.FriendsService
import javax.inject.Inject

class RemoteFriendsDataSourceImpl @Inject constructor(
    private val friendsService: FriendsService
) : RemoteFriendsDataSource {
    override suspend fun getFriendList(
        userId: Int,
        page: Int,
        size: Int
    ): NetworkState<BaseResponse<FriendListResponse>> {
        return friendsService.getFriendList(userId, page, size)
    }
}