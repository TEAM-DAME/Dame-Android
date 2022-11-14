package com.yangbong.set_profile

import android.os.Bundle
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ActivitySetProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SetProfileActivity : BindingActivity<ActivitySetProfileBinding>(R.layout.activity_set_profile) {

    private val setProfileViewModel by viewModels<SetProfileViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}