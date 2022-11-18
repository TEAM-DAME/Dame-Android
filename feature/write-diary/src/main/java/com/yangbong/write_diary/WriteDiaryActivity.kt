package com.yangbong.write_diary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        textcheck()
        //naver clova api 이용하기위한 링크들 . .
        
    }
    fun emotion(){

    }
    fun init(){
        binding.completeBtn.setOnClickListener{  // 완료 버튼시 dialog
            val dlg=diary_dialog(this)
            dlg.show()
        }

    }

    fun textcheck(){
        var titleCheck =false
        var contentCheck=false

        binding.diaryTitle.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                titleCheck = binding.diaryTitle.length() >= 1
                if(titleCheck && contentCheck) {
                    binding.completeBtn.isClickable = true
                    binding.completeBtn.isEnabled = true
                    binding.completeBtn.setBackgroundResource(com.yangbong.damedame.shared.R.color.able_btn)
                }
                else{
                    binding.completeBtn.isClickable = false
                    binding.completeBtn.isEnabled = false
                    binding.completeBtn.setBackgroundResource(com.yangbong.damedame.shared.R.color.disable_btn)
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        binding.diaryText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                contentCheck = binding.diaryText.length() >= 1
                if(titleCheck && contentCheck) {
                    binding.completeBtn.isClickable = true
                    binding.completeBtn.isEnabled = true
                    binding.completeBtn.setBackgroundResource(com.yangbong.damedame.shared.R.color.able_btn)
                }
                else{
                    binding.completeBtn.isClickable = false
                    binding.completeBtn.isEnabled = false
                    binding.completeBtn.setBackgroundResource(com.yangbong.damedame.shared.R.color.disable_btn)
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

    } // title , text 비어있는지확인해서 button 활성화 / 비활성화

}