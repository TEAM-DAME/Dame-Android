package com.yangbong.set_profile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.constant.SetProfileIdConstant.*
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ActivitySetProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetProfileActivity : BindingActivity<ActivitySetProfileBinding>(R.layout.activity_set_profile) {
    private val setProfileViewModel by viewModels<SetProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setProfileViewModel = setProfileViewModel
        initProfileIdLengthMessage()
        initNextButtonClickListener()
    }

    private fun initProfileIdLengthMessage() {
        binding.etSetProfileId.addTextChangedListener {
            val message = when (binding.etSetProfileId.text.length) {
                in 0..9 -> HAS_NO_STATE
                10 -> OVER_TEXT_LIMIT
                else -> throw IllegalStateException("Maximum nickname length is 10")
            }
            setProfileViewModel.updateProfileIdState(message)
        }
    }

    private fun initNextButtonClickListener() {
        binding.btnNext.setOnSingleClickListener {
            setProfileViewModel.postSetProfile()
        }
    }

}