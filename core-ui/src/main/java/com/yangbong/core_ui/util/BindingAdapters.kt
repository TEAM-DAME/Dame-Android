package com.yangbong.core_ui.util

import android.view.View
import androidx.databinding.BindingAdapter
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
