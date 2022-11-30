package com.yangbong.damedame.notification.data

enum class EmotionType(
    val emotion: String,
    val emotionTypeNumber: Int
) {
    POSITIVE("긍정", 0),
    NEGATIVE("부정", 1),
    NEUTRAL("중립", 2);
}
