package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainCharacterRequest

interface SetCharacterRepository {

    suspend fun postCharacter(characterRequest: DomainCharacterRequest): Result<String>
}