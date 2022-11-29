package com.yangbong.damedame

import android.app.Application
import android.content.Context
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.storage.s3.AWSS3StoragePlugin
import com.google.firebase.FirebaseApp
import com.kakao.sdk.common.KakaoSdk
import com.yangbong.core_ui.util.DameDameDebugTree
import com.yangbong.damedame.BuildConfig.KAKAO_NATIVE_APP_KEY
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class DameDameApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initKakaoSdk(this)
        initFirebaseApp(this)
        initAmplify(applicationContext)
    }
}

private fun initTimber() {
    if (BuildConfig.DEBUG) {
        Timber.plant(DameDameDebugTree())
    }
}

private fun initKakaoSdk(context: Context) {
    KakaoSdk.init(context, KAKAO_NATIVE_APP_KEY)
}

private fun initFirebaseApp(context: Context) {
    FirebaseApp.initializeApp(context)
}

private fun initAmplify(context: Context) {
    try {
        Amplify.addPlugin(AWSCognitoAuthPlugin())
        Amplify.addPlugin(AWSS3StoragePlugin())
        Amplify.configure(context)
        Timber.d("Initialized Amplify")
    } catch (error: AmplifyException) {
        Timber.d("Could not initialize Amplify $error")
    }
}