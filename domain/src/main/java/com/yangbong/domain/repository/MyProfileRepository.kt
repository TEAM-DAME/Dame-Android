package com.yangbong.domain.repository

import com.yangbong.domain.entity.MyProfileInfo

interface MyProfileRepository {

    suspend fun getMyProfileInfo(): Result<MyProfileInfo>
}