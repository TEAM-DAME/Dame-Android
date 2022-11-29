package com.yangbong.domain.repository

import com.yangbong.domain.entity.request.DomainWriteDiaryRequest

interface WriteDiaryRepository {
    fun getUserId():Int

    suspend fun postWriteDiary(WriteRequest:DomainWriteDiaryRequest):Result<String>
}