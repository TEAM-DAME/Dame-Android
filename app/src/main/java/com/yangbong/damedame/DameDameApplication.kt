package com.yangbong.damedame

import android.app.Application
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import com.yangbong.core_ui.util.DameDameDebugTree
import com.yangbong.damedame.BuildConfig.KAKAO_NATIVE_APP_KEY
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

//test
@HiltAndroidApp
class DameDameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DameDameDebugTree())
        }
        KakaoSdk.init(this, KAKAO_NATIVE_APP_KEY)
        FirebaseApp.initializeApp(this)
    }
}