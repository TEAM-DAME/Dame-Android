package com.yangbong.data.remote.mapper

import com.yangbong.data.remote.model.response.Diary
import com.yangbong.domain.entity.DiaryInfo
import javax.inject.Inject

class DiaryMapper @Inject constructor() {

    fun toDiaryInfo(diaryItem: Diary): DiaryInfo =
        DiaryInfo(
            diaryId = diaryItem.diaryId,
            userId = diaryItem.userId,
            title = diaryItem.title,
            content = diaryItem.content,
            positive = diaryItem.positive,
            neutral = diaryItem.neutral,
            negative = diaryItem.negative,
            visibility = diaryItem.visibility,
            diaryTime = diaryItem.diaryTime,
            minionId = diaryItem.minionId
        )
}