package com.yangbong.damedame.write_diary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.yangbong.damedame.write_diary.databinding.ActivityWriteDiaryBinding

class write_diaryActivity : AppCompatActivity() {
    lateinit var binding:ActivityWriteDiaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWriteDiaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mainfrag=FriendsSearchFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frag,mainfrag).commit()
    }
}