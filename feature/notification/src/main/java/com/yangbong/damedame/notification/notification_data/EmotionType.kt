package com.yangbong.damedame.notification.notification_data

import com.yangbong.damedame.notification.R

enum class EmotionType(
    val emotion: String,
    val emotionImgSrc: Int
) {
    POSITIVE("긍정", com.yangbong.damedame.shared.R.drawable.img_emotion_positive),
    NEGATIVE("부정", com.yangbong.damedame.shared.R.drawable.img_emotion_positive),
    NEUTRAL("중립", com.yangbong.damedame.shared.R.drawable.ic_neutral)
}
