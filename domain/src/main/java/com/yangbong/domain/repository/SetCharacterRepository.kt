package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainCharacterRequest

interface SetCharacterRepository {

    fun getUserId(): Int

    suspend fun postCharacter(characterRequest: DomainCharacterRequest): Result<String>
}