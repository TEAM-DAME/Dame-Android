package com.yangbong.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kakao.sdk.common.util.Utility
import com.yangbong.auth.login.AutoLoginConstant
import com.yangbong.auth.login.LoginFragment
import com.yangbong.auth.login.LoginViewModel
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.extension.replace
import com.yangbong.damedame.auth.R
import com.yangbong.damedame.auth.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BindingActivity<ActivityAuthBinding>(R.layout.activity_auth) {
    private val loginViewModel by viewModels<LoginViewModel>()
    lateinit var autoLoginState: AutoLoginConstant

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        checkAutoLoginState()
        autoLogin()

    }

    private fun autoLogin() {
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (::autoLoginState.isInitialized) {
                        if (autoLoginState == AutoLoginConstant.AUTO_LOGIN_SUCCESS) {
                            mainNavigator.navigateMain(this@AuthActivity)
                            finish()
                        } else {
                            replace<LoginFragment>(R.id.container_auth)
                        }
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
    }

    private fun checkAutoLoginState() {
        autoLoginState = if (loginViewModel.getAccessToken() != "") {
            AutoLoginConstant.AUTO_LOGIN_SUCCESS
        } else {
            AutoLoginConstant.AUTO_LOGIN_FAILURE
        }
    }

}