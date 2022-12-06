package com.yangbong.data.remote.mapper

import com.yangbong.data.remote.model.response.Friend
import com.yangbong.domain.entity.FriendProfileInfo
import javax.inject.Inject

class FriendMapper @Inject constructor() {

    fun toFriendInfo(friendItem: Friend): FriendProfileInfo =
        FriendProfileInfo(
            userId = friendItem.userId,
            userProfileImageUrl = friendItem.userProfileImageUrl,
            userNickname = friendItem.userNickname
        )
}