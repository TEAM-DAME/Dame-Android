package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.response.FriendListResponse
import com.yangbong.data.remote.service.FriendListService
import javax.inject.Inject

class RemoteFriendListDataSourceImpl @Inject constructor(
    private val friendListService: FriendListService
):RemoteFriendListDataSource{

    override suspend fun getFriendList(
        userId: Int,
        page: Int,
        size: Int
    ): NetworkState<FriendListResponse> {
        return friendListService.getFriendList(userId,page, size)
    }
}