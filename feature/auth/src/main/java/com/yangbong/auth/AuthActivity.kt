package com.yangbong.auth

import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.damedame.set_profile.R
import com.yangbong.damedame.set_profile.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BindingActivity<ActivityAuthBinding>(R.layout.activity_auth) {
    private val loginViewModel by viewModels<LoginViewModel>()
}