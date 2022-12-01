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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        setDate()
        init()
        textcheck()

    }
    fun setDate(){
        val curdata= Date(System.currentTimeMillis())
        val date= SimpleDateFormat("yyyy년 MM월 dd일", Locale("ko","KR"))
        val day= SimpleDateFormat("E요일", Locale("ko","KR"))
        binding.date.text=date.format(curdata)
        binding.day.text=day.format(curdata)
    }

    fun emotion(){
        val title=binding.diaryTitle.text.toString()
        val content=binding.diaryText.text.toString()


        // 감정 분석 api 쏘고 결과값 받아오고 저장
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO){

            }
        }
    }
    fun init(){

        binding.completeBtn.setOnClickListener{  // 완료 버튼시 dialog
            val dlg=diary_dialog(this)
            var check=false
            dlg.show()
            dlg.setOnClickedListener(object :diary_dialog.ButtonClickListener{
                override fun onClicked(isok: Boolean) {
                    check=isok
                    if(check){
                        //writeDiaryViewModel.postWriteDiary()
                        Log.i("test","ok")
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