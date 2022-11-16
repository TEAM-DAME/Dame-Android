package com.yangbong.core_ui.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.yangbong.core_ui.constant.SetProfileIdConstant
import com.yangbong.core_ui.constant.SetProfileIdConstant.*
import com.yangbong.damedame.shared.R


@BindingAdapter("setProfileUnderScoreViewBackGround")
fun View.setProfileUnderScoreViewBackGround(profileIdStateNumber: SetProfileIdConstant?) {
    if (profileIdStateNumber != null) {
        this.setBackgroundResource(
            when (profileIdStateNumber) {
                OVER_TEXT_LIMIT -> R.color.pink_ff6161
                NO_SPECIAL_CHARACTER -> R.color.pink_ff6161
                DUPLICATE_NICKNAME -> R.color.pink_ff6161
                ALLOWED_NICKNAME -> R.color.gray_2
                HAS_NO_STATE -> R.color.gray_2
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
