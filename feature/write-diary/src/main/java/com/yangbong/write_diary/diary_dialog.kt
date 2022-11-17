package com.example.yangbong

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.example.yangbong.databinding.DiaryDialogBinding

class diary_dialog (private val context:AppCompatActivity){
    lateinit var  binding:DiaryDialogBinding
    private val dlg= Dialog(context)

    fun show(){
        binding= DiaryDialogBinding.inflate(context.layoutInflater)
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(binding.root)
        dlg.setCancelable(false)
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.okBtn.setOnClickListener {
            dlg.dismiss()
        }
        binding.noBtn.setOnClickListener{
            dlg.dismiss()
        }
        dlg.show()
    }
}