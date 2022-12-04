package com.yangbong.domain.repository

import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.entity.response.DomainPocketResponse

interface PocketRepository {
    suspend fun getUserPocket(userId:Int):Result<DomainPocketResponse>
    fun getUserId():Int
    fun getUserNickName():String
}