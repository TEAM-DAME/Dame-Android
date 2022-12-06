package com.yangbong.domain.repository

import com.yangbong.domain.entity.FriendProfileInfo

interface FriendsRepository {

    suspend fun getFriendList(userId: Int, page: Int, size: Int): Result<List<FriendProfileInfo>>
}