package com.yangbong.domain.repository

import com.yangbong.domain.entity.response.DomainCharacterResponse

interface HomeRepository {

    suspend fun getCharacterInfo(userId: Int): Result<DomainCharacterResponse>
}