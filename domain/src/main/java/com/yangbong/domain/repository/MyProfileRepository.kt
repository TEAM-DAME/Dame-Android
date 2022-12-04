package com.yangbong.domain.repository

import com.yangbong.domain.entity.MyProfileInfo
import com.yangbong.domain.entity.MyProfileUser

interface MyProfileRepository {

    suspend fun getMyProfileInfo(): Result<MyProfileUser>

}