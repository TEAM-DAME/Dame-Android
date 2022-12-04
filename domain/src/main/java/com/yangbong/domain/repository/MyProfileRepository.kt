package com.yangbong.domain.repository

import com.yangbong.domain.entity.DiaryInfo
import com.yangbong.domain.entity.MyProfileInfo

interface MyProfileRepository {

    suspend fun getMyProfileInfo(userId: Int): Result<MyProfileInfo>

    suspend fun getDiaryList(userId: Int, page: Int, size: Int): Result<List<DiaryInfo>>

    fun getUserId(): Int
}