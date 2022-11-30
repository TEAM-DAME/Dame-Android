package com.yangbong.damedame.notification.notification_data

import com.yangbong.damedame.notification.R

enum class EmotionType(
    val emotion: String,
    val emotionTypeNumber: Int
) {
    POSITIVE("긍정", 0),
    NEGATIVE("부정", 1),
    NEUTRAL("중립", 2);

    fun findByTypeNumber(inputEmotionTypeNumber: Int): EmotionType {
        return values().find {
            it.emotionTypeNumber == inputEmotionTypeNumber
        }!!
    }
}
