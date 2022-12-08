package com.yangbong.main.diary

import android.os.Bundle
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.ActivityDiaryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryDetailActivity :
    BindingActivity<ActivityDiaryDetailBinding>(R.layout.activity_diary_detail) {
    private val diaryDetailViewModel: DiaryDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getDiaryContent()
        observeDiaryContent()
        onBackButtonClick()
    }

    private fun getDiaryContent() {
        val userId = getUserIdFromExtra()
        val diaryId = getDiaryIdFromExtra()
        diaryDetailViewModel.getDiaryContent(userId, diaryId)
    }

    private fun observeDiaryContent() {
        diaryDetailViewModel.diaryContent.observe(this) {
            binding.diary = it
        }
    }

    private fun onBackButtonClick() {
        binding.btnBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun getUserIdFromExtra(): Int = intent.getIntExtra(USER_ID, -1)

    private fun getDiaryIdFromExtra(): Int = intent.getIntExtra(DIARY_ID, -1)


    companion object {
        const val USER_ID = "USER_ID"
        const val DIARY_ID = "DIARY_ID"
    }
}