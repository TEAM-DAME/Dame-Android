package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainSelectCharacterRequest
import com.yangbong.domain.entity.response.DomainSelectCharacterResponse

interface SelectCharacterRepository {
    suspend fun putInitCharacter(selectCharacterRequest: DomainSelectCharacterRequest):Result<DomainSelectCharacterResponse>

    fun saveUserId(userid : Int)

    fun saveInitCharacter(minion : Int)
}