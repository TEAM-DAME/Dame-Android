package com.yangbong.domain.repository

import com.yangbong.domain.entity.ProfileInfo

interface FriendsRepository {
    suspend fun getUserProfileInfo(userId:Int):Result<ProfileInfo>
}