package com.yangbong.damedame.notification

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.damedame.notification.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var adapter: SettingRecyclerViewAdapter
    lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SettingRecyclerViewAdapter(listOf("알림", "정책 및 보안", "앱 버전"))

        binding.settingRecycler.layoutManager=
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.settingRecycler.adapter = adapter
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