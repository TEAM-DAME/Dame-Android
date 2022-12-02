package com.yangbong.write_diary

import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.damedame.write_diary.R
import com.yangbong.damedame.write_diary.databinding.ActivityWriteDiaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteDiaryActivity :
    BindingActivity<ActivityWriteDiaryBinding>(R.layout.activity_write_diary) {

    private val writeDiaryViewModel by viewModels<WriteDiaryViewModel>()


}