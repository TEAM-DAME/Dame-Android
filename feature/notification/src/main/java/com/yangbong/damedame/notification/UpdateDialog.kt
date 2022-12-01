package com.yangbong.damedame.notification

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import com.yangbong.damedame.notification.databinding.UpdateVersionDialogBinding

class UpdateDialog(private val context: AppCompatActivity) {

    private val binding: UpdateVersionDialogBinding =
        UpdateVersionDialogBinding.inflate(context.layoutInflater)

    private val dialog = Dialog(context)

    init{

        binding.updateButton.setOnClickListener {
            //google play store로 이동하기
            dialog.dismiss()
        }
        binding.cancelButton.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun show() {

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.show()
    }
}