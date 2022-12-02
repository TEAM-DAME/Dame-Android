package com.yangbong.data.remote.mapper

import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.model.response.Diary
import com.yangbong.data.remote.model.response.EmotionTypeWithValue
import com.yangbong.domain.entity.DiaryInfo
import com.yangbong.domain.entity.EmotionInfo
import com.yangbong.domain.entity.request.EmotionValue
import javax.inject.Inject

class DiaryMapper @Inject constructor(
    val dataSource: LocalPreferenceUserDataSource
) {
    fun toDiaryInfo(diary: Diary): DiaryInfo {
        return DiaryInfo(
            id = diary.id,
            emotion = toEmotionInfo(diary.emotionTypeWithValue),
            title = diary.title,
            date = diary.date,
            isLocked = diary.isLocked
        )
    }

    private fun toEmotionInfo(emotionTypeWithValue: EmotionTypeWithValue): EmotionInfo {
        return EmotionInfo(
            emotionType = emotionTypeWithValue.emotionType,
            emotionValue = emotionTypeWithValue.emotionValue
        )
    }
}