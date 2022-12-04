package com.yangbong.domain.repository

import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.entity.response.DomainFriendListResponse

interface FriendsRepository {
    suspend fun getUserProfileInfo(userId:Int):Result<ProfileInfo>
    suspend fun getFriendList(userId:Int,page:Int,size:Int):Result<DomainFriendListResponse>
    fun getUserId():Int
    fun getUserNickName():String
}