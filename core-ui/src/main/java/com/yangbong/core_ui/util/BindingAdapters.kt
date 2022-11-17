package com.yangbong.core_ui.util

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.CircleCropTransformation
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
                ALLOWED_NICKNAME -> R.color.main_blue
                HAS_NO_STATE -> R.color.gray_1
            }
        )
    }
}

@BindingAdapter("setProfileButtonBackground")
fun AppCompatButton.setProfileButtonBackground(profileIdStateNumber: SetProfileIdConstant?) {
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

@BindingAdapter("setProfileImage")
fun ImageView.setProfileImage(profileImageUrl: String?) {
    this.load(profileImageUrl) {
        transformations(CircleCropTransformation())
        error(R.drawable.img_profile_tmp_01)
        placeholder(R.drawable.img_profile_tmp_01)
    }
}
