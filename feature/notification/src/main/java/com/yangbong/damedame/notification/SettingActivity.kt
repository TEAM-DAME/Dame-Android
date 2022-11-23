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
import com.yangbong.damedame.notification.databinding.ActivitySettingBinding
import timber.log.Timber

class SettingActivity : AppCompatActivity() {

    private lateinit var adapter: SettingRecyclerViewAdapter
    private lateinit var binding: ActivitySettingBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //timber 초기화
        Timber.plant(Timber.DebugTree())

        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SettingRecyclerViewAdapter(
            listOf(
                getString(com.yangbong.damedame.shared.R.string.notification),
                getString(com.yangbong.damedame.shared.R.string.policy_security),
                getString(com.yangbong.damedame.shared.R.string.app_version),
            )
        )

        binding.settingRecycler.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.settingRecycler.adapter = adapter
    }

    private fun initRemoteConfig() {
        remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 100
            mapOf(
                "fortesting" to "xxx",
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

    private fun checkVersion() {
        val latestVersion = remoteConfig.getString("app_version")
        activateRemoteConfig()
        Timber.d("getAppVersion: $latestVersion")
        val currentVersion = getCurrentAppVersion(this)

//      ㄱ binding.currentAppVersionText.text = currentVersion
//        binding.latestAppVersionText.text = latestVersion

//        if (currentVersion != latestVersion) {
//            showUpdateDialog()
//        }
    }
}