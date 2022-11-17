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

class SettingActivity : AppCompatActivity() {

    private lateinit var adapter: SettingRecyclerViewAdapter
    private lateinit var binding: ActivitySettingBinding
    private lateinit var remoteConfig: FirebaseRemoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SettingRecyclerViewAdapter(listOf("알림", "정책 및 보안", "앱 버전"))

        binding.settingRecycler.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.settingRecycler.adapter = adapter
    }

    private fun initRemoteConfig() {
        remoteConfig = Firebase.remoteConfig
        remoteConfig.setConfigSettingsAsync(
            remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            }
        )
    }

    private fun getCurrentAppVersion(context: Context): String {
        var appVersion = ""

        runCatching {
            appVersion = context.packageManager
                .getPackageInfo(context.packageName, 0)
                .versionName
        }.onFailure { e ->
            Log.e("getAppVersion", e.toString())
        }

        // 문자를 제거하고 버전 숫자만 리턴하기 위해서
        // ex)1.0.0 -> 1.0.0
        appVersion = appVersion.replace("[a-zA-Z]|-".toRegex(), "")

        return appVersion
    }
}