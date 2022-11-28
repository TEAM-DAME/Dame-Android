package com.yangbong.write_diary

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.yangbong.damedame.write_diary.databinding.DiaryDialogBinding

class diary_dialog (private val context:AppCompatActivity){
    lateinit var  binding:DiaryDialogBinding
    private val dlg= Dialog(context)

    interface ButtonClickListener{
        fun onClicked(isok:Boolean)
    }
    lateinit var onClickListener: ButtonClickListener
    fun setOnClickedListener(listener: ButtonClickListener){
        onClickListener=listener
    }

    fun show(){
        binding= DiaryDialogBinding.inflate(context.layoutInflater)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)
        dlg.setCancelable(false)
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.okBtn.setOnClickListener {

            onClickListener.onClicked(true)
            dlg.dismiss()
        }
        binding.noBtn.setOnClickListener{
            onClickListener.onClicked(false)

            dlg.dismiss()
        }
        dlg.show()
    }
}