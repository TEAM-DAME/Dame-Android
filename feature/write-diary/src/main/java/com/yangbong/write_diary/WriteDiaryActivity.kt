package com.yangbong.write_diary

import android.os.Bundle
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.EventObserver
import com.yangbong.damedame.shared.custom.DameDameDialogFragment
import com.yangbong.damedame.write_diary.R
import com.yangbong.damedame.write_diary.databinding.ActivityWriteDiaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteDiaryActivity :
    BindingActivity<ActivityWriteDiaryBinding>(R.layout.activity_write_diary) {

    private val writeDiaryViewModel by viewModels<WriteDiaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        writeDiaryViewModel.getUserId()
        initClickEvent()
        initPostDiaryObserver()
        initMoveToHomeObserver()
    }

    private fun initClickEvent() {
        binding.btnBack.setOnSingleClickListener {
            showDialog(
                title = getString(com.yangbong.damedame.shared.R.string.exit_diary_dialog_title),
                body = getString(com.yangbong.damedame.shared.R.string.exit_diary_dialog_content),
                positiveButtonClickListener = { finish() }
            )
        }
        binding.btnComplete.setOnSingleClickListener {
            showDialog(
                title = getString(com.yangbong.damedame.shared.R.string.post_diary_dialog_title),
                body = getString(com.yangbong.damedame.shared.R.string.post_diary_dialog_content),
                positiveButtonClickListener = ::startSentimentAnalyze
            )
        }
    }

    private fun showDialog(title: String, body: String, positiveButtonClickListener: () -> Unit) {
        DameDameDialogFragment.newInstance(
            title = title,
            body = body,
            positiveButtonClickListener = positiveButtonClickListener
        ).show(
            supportFragmentManager,
            DameDameDialogFragment.TAG
        )
    }

    private fun startSentimentAnalyze() {
        writeDiaryViewModel.postSentiment(binding.etContent.text.toString())
    }

    private fun initPostDiaryObserver() {
        writeDiaryViewModel.postDiary.observe(
            this,
            EventObserver {
                postDiary()
            }
        )
    }

    private fun postDiary() {
        writeDiaryViewModel.postDiary(writeDiaryViewModel.userId.value ?: -1)
    }

    private fun initMoveToHomeObserver() {
        writeDiaryViewModel.moveToHome.observe(
            this,
            EventObserver {
                moveMainActivity()
            }
        )
    }

    private fun moveMainActivity() {
        mainNavigator.navigateMain(this)
        finish()
    }

}