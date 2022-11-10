package com.yangbong.damedame.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.damedame.settings.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    lateinit var adapter: SettingRecyclerViewAdapter
    lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SettingRecyclerViewAdapter(listOf("알림", "정책 및 보안", "앱 버전"))

        binding.settingRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.settingRecycler.adapter = adapter
    }
}