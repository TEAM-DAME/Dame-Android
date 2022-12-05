package com.yangbong.domain.repository

import com.yangbong.domain.entity.CharacterInfo

interface PocketRepository {

    suspend fun getCharacterList(userId: Int): Result<List<CharacterInfo>>
}