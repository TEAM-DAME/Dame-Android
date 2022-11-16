package com.yangbong.write_diary

import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.write_diary.R
import com.yangbong.damedame.write_diary.databinding.ActivityWriteDiaryBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WriteDiaryActivity:
BindingActivity<ActivityWriteDiaryBinding>(R.layout.activity_write_diary){

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics

}