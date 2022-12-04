package com.yangbong.damedame.notification

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.damedame.notification.databinding.ActivitySettingBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class SettingActivity :
    BindingActivity<ActivitySettingBinding>(R.layout.activity_setting) {
    private lateinit var remoteConfig: FirebaseRemoteConfig
    private val currentVersion: String by lazy {
        getCurrentAppVersion(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //timber 초기화
        Timber.plant(Timber.DebugTree())
        //remoteConfig 초기화
        initRemoteConfig()

        initView()
    }


    private fun initRemoteConfig() {

        remoteConfig = FirebaseRemoteConfig.getInstance()

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 100
            mapOf(
                "app_version" to "0.0.0"
            )
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    private fun getCurrentAppVersion(context: Context): String {

        var appVersion = ""

        runCatching {
            appVersion = context.packageManager
                .getPackageInfo(context.packageName, 0)
                .versionName
        }.onFailure { e ->
            Timber.d("getAppVersion: $e")
        }.onSuccess { v ->
            Timber.d("getAppVersion: $v")
        }

        // 문자를 제거하고 버전 숫자만 리턴하기 위해서
        // ex)1.0.0 -> 1.0.0
        appVersion = appVersion.replace("[a-zA-Z]|-".toRegex(), "")

        return appVersion
    }

    private fun activateRemoteConfig() {

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                val result = task.result

                if (task.isSuccessful) {
                    Timber.d("activateRemoteConfig: $result")
                } else {
                    Timber.d("activateRemoteConfig: $result")
                }
            }
    }

    private fun initView() {

        Timber.d("currentAppVersion : $currentVersion")

        NavigatorIntentObject.setMainIntent(mainNavigator.navigateNotificationFromMain(this))

        binding.latestAppVersionText.text = currentVersion

        binding.latestAppVersionText.setOnClickListener {
            checkIsLatestVersion()
        }

        binding.enterNotificationImg.setOnClickListener {
            NotificationDialog(this).show()
        }
        binding.backButton.setOnClickListener {
            finish()
        }
    }


    private fun checkIsLatestVersion() {

        activateRemoteConfig()
        Timber.d("latestVersion : ${remoteConfig.getString("app_version")}")

        val currentVersion = getCurrentAppVersion(this)

        if (remoteConfig.getString("app_version") != currentVersion) {
            UpdateDialog(this).show()
        }
    }


}