package com.yangbong.data.remote.mapper

import com.yangbong.data.local.data_source.LocalPreferenceUserDataSource
import com.yangbong.data.remote.model.response.Diary
import com.yangbong.data.remote.model.response.Emotion
import com.yangbong.domain.entity.DiaryInfo
import com.yangbong.domain.entity.EmotionInfo
import javax.inject.Inject

class DiaryMapper @Inject constructor(
    val dataSource: LocalPreferenceUserDataSource
) {
    fun toDiaryInfo(diary: Diary): DiaryInfo {
        return DiaryInfo(
            id = diary.id,
            emotion = toEmotionInfo(diary.emotion),
            title = diary.title,
            date = diary.date,
            isLocked = diary.isLocked
        )
    }

    private fun toEmotionInfo(emotion: Emotion): EmotionInfo {
        return EmotionInfo(
            emotionType = emotion.emotionType,
            emotionValue = emotion.emotionValue
        )
    }
}