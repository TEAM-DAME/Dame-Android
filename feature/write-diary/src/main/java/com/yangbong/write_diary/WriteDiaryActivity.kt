package com.yangbong.write_diary

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.write_diary.R
import com.yangbong.damedame.write_diary.databinding.ActivityWriteDiaryBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

import javax.inject.Inject

@AndroidEntryPoint
class WriteDiaryActivity:
BindingActivity<ActivityWriteDiaryBinding>(R.layout.activity_write_diary){
    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics
    private val writeDiaryViewModel by viewModels<WriteDiaryViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.writeDiaryViewModel=writeDiaryViewModel
        writeDiaryViewModel.getUserId()
        setDate()
        textcheck()
        init()

    }
    fun setDate(){
        val curdata= Date(System.currentTimeMillis())
        val date= SimpleDateFormat("yyyy년 MM월 dd일", Locale("ko","KR"))
        val day= SimpleDateFormat("E요일", Locale("ko","KR"))
        binding.date.text=date.format(curdata)
        binding.day.text=day.format(curdata)
    }


    fun init(){

        binding.completeBtn.setOnClickListener{  // 완료 버튼시 dialog
            val dlg=DiaryDialog(this)
            var check=false
            dlg.show()
            dlg.setOnClickedListener(object :DiaryDialog.ButtonClickListener{
                override fun onClicked(isok: Boolean) {
                    check=isok
                    if(check){
                        var title=binding.diaryTitle.text.toString()
                        var content=binding.diaryText.text.toString()
                        writeDiaryViewModel.getData(content,title)
                        writeDiaryViewModel.postSentiment(title+content)
                    }
                    else{
                        Log.i("test","no")
                    }
                }
            })
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