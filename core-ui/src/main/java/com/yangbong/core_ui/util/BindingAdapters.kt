package com.yangbong.core_ui.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Layout
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yangbong.core_ui.constant.SetProfileNicknameConstant
import com.yangbong.core_ui.constant.SetProfileNicknameConstant.*
import com.yangbong.damedame.shared.R
import kotlin.math.max
import kotlin.math.roundToInt


@BindingAdapter("profileIdStateNumber")
fun TextView.setProfileIdState(profileIdStateNumber: SetProfileNicknameConstant?) {
    val context = this.context

    if (profileIdStateNumber != null) {
        when (profileIdStateNumber) {
            OVER_TEXT_LIMIT -> {
                setTextColor(context.getColor(R.color.pink_ff6161))
                setText(R.string.set_profile_id_over_text_limit)
            }
            NO_SPECIAL_CHARACTER -> {
                setTextColor(context.getColor(R.color.pink_ff6161))
                setText(R.string.set_profile_id_no_special_character)
            }
            DUPLICATE_NICKNAME -> {
                setTextColor(context.getColor(R.color.pink_ff6161))
                setText(R.string.set_profile_id_duplicate_nickname)
            }
            ALLOWED_NICKNAME -> {
                setTextColor(context.getColor(R.color.main_blue))
                setText(R.string.set_profile_id_allowed_nickname)
            }
            HAS_NO_STATE -> {
                text = ""
            }
        }
    }
}

@BindingAdapter("setProfileUnderScoreViewBackGround")
fun View.setProfileUnderScoreViewBackGround(profileIdStateNumber: SetProfileNicknameConstant?) {
    if (profileIdStateNumber != null) {
        this.setBackgroundResource(
            when (profileIdStateNumber) {
                OVER_TEXT_LIMIT -> R.color.pink_ff6161
                NO_SPECIAL_CHARACTER -> R.color.pink_ff6161
                DUPLICATE_NICKNAME -> R.color.pink_ff6161
                ALLOWED_NICKNAME -> R.color.main_blue
                HAS_NO_STATE -> R.color.gray_1
            }
        )
    }
}

@BindingAdapter("setProfileButtonBackground")
fun AppCompatButton.setProfileButtonBackground(profileIdStateNumber: SetProfileNicknameConstant?) {
    if (profileIdStateNumber != null) {
        this.setBackgroundResource(
            when (profileIdStateNumber) {
                OVER_TEXT_LIMIT -> R.drawable.bg_button_red_15_dp
                NO_SPECIAL_CHARACTER -> R.drawable.bg_button_red_15_dp
                DUPLICATE_NICKNAME -> R.drawable.bg_button_red_15_dp
                ALLOWED_NICKNAME -> R.drawable.bg_button_blue_15_dp
                HAS_NO_STATE -> R.drawable.bg_button_gray_15_dp
            }
        )
    }
}

@BindingAdapter("setCharacterLogo")
fun ImageView.setCharacterLogo(characterLogoStatic: Drawable) = Glide.with(this)
    .load(R.drawable.login_character)
    .placeholder(characterLogoStatic)
    .error(characterLogoStatic)
    .diskCacheStrategy(DiskCacheStrategy.NONE)
    .apply(RequestOptions().centerCrop())
    .into(this)

@BindingAdapter("setCharacterAnimatedImage")
fun ImageView.setCharacterAnimatedImage(characterLogoStatic: Drawable) = Glide.with(this)
    .load(R.drawable.login_character)
    .placeholder(characterLogoStatic)
    .error(characterLogoStatic)
    .diskCacheStrategy(DiskCacheStrategy.NONE)
    .apply(RequestOptions().centerCrop())
    .into(this)

@BindingAdapter("setProfileImage")
fun ImageView.setProfileImage(profileImageUrl: String?) {
    this.load(profileImageUrl) {
        transformations(CircleCropTransformation())
        error(R.color.transparent)
        placeholder(R.color.transparent)
    }
}

@BindingAdapter("setCharacterImage")
fun ImageView.setCharacterImage(characterId: Int) {
    val drawableResource = "img_character_$characterId"
    val packageName = this.context.packageName
    val resource = this.context.resources.getIdentifier(drawableResource, "drawable", packageName)
    Glide.with(this)
        .load(resource)
        .placeholder(R.color.transparent)
        .error(R.color.transparent)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .apply(RequestOptions().centerCrop())
        .into(this)
}

@BindingAdapter("setCharacterDescription")
fun TextView.setCharacterDescription(characterId: Int) {
    val context = this.context
    val packageName = context.packageName
    val stringResource = "character_description_$characterId"
    val resource = context.resources.getIdentifier(stringResource, "string", packageName)
    text = resources.getString(resource)
}

@BindingAdapter("setCharacterNickname")
fun TextView.setCharacterNickname(characterId: Int) {
    val context = this.context
    val packageName = context.packageName
    val stringResource = "character_name_$characterId"
    val resource = context.resources.getIdentifier(stringResource, "string", packageName)
    text = resources.getString(resource)
}

@BindingAdapter("setCharacterAnimatedBackground")
fun LottieAnimationView.setCharacterAnimatedBackground(characterId: Int) {
    val rawResource = "wave_$characterId.json"
    this.setAnimation(rawResource)
}

@BindingAdapter("positive", "neutral", "negative")
fun TextView.setMaxEmotionValue(
    positive: Double,
    neutral: Double,
    negative: Double
) {
    val maxValue = max(max(positive, neutral), negative)
    text = maxValue.roundToInt().toString() + "%"
}

@BindingAdapter("setDate")
fun TextView.setDate(rawDate: String) {
    // TODO("서버에서 시간 포맷 수정해주면 다시 수정하기!")
    val date = rawDate.split("T")
    text = date[0]
}

@BindingAdapter("setIsLocked")
fun AppCompatImageButton.setIsLocked(visibility: Int) {
    if (mapToBoolean(visibility)) setImageResource(R.drawable.ic_unlock)
    else setImageResource(R.drawable.ic_lock)
}

private fun mapToBoolean(visibility: Int): Boolean {
    return if (visibility == 0) false
    else visibility == 1
}

@BindingAdapter("positive", "neutral", "negative")
fun ImageView.setEmotionCharacter(
    positive: Double,
    neutral: Double,
    negative: Double
) {
    val emotionMap = hashMapOf<String, Double>()
    emotionMap["positive"] = positive
    emotionMap["neutral"] = neutral
    emotionMap["negative"] = negative

    val maxValue = emotionMap.maxOfOrNull { it.value }
    val keys = emotionMap.filterValues { it == maxValue }.keys

    if (keys.contains("positive")) {
        setImageResource(R.drawable.img_emotion_positive)
    } else if (keys.contains("neutral")) {
        setImageResource(R.drawable.img_emotion_neutral)
    } else {
        setImageResource(R.drawable.img_emotion_negative)
    }
}

@BindingAdapter("positive", "neutral", "negative")
fun ConstraintLayout.setEmotionLayout(
    positive: Double,
    neutral: Double,
    negative: Double
) {
    val emotionMap = hashMapOf<String, Double>()
    emotionMap["positive"] = positive
    emotionMap["neutral"] = neutral
    emotionMap["negative"] = negative

    val maxValue = emotionMap.maxOfOrNull { it.value }
    val keys = emotionMap.filterValues { it == maxValue }.keys

    if (keys.contains("positive")) {
        setBackgroundResource(R.drawable.bg_button_positive_20_dp)
    } else if (keys.contains("neutral")) {
        setBackgroundResource(R.drawable.bg_button_neutral_20_dp)
    } else {
        setBackgroundResource(R.drawable.bg_button_negative_20_dp)
    }
}

