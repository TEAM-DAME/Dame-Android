package com.yangbong.domain.repository

import com.yangbong.domain.entity.Diary

interface DiaryRepository {

    suspend fun getDiaryContent(userId: Int, diaryId: Int): Result<Diary>
}