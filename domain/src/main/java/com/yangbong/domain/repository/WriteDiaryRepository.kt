package com.yangbong.domain.repository

import com.yangbong.domain.entity.EmotionInfo
import com.yangbong.domain.entity.request.DomainDiaryRequest

interface WriteDiaryRepository {

    suspend fun postSentiment(content: String): Result<EmotionInfo>

    suspend fun postDiary(diaryRequest: DomainDiaryRequest): Result<String>

    fun getUserId(): Int
}