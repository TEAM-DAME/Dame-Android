package com.yangbong.damedame.notification

import android.app.Dialog
import android.content.ContentValues
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.testworkmanager.NotificationWorker
import com.yangbong.damedame.notification.databinding.NotificationDialogBinding
import com.yangbong.damedame.notification.databinding.UpdateVersionDialogBinding
import com.yangbong.damedame.notification.util.getWaitingTimeBeforeNotification
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class NotificationDialog(private val context: AppCompatActivity) {

    private val binding: NotificationDialogBinding =
        NotificationDialogBinding.inflate(context.layoutInflater)

    private val dialog = Dialog(context)

    init {

        binding.settingButton.setOnClickListener {
            NotificationWorker.notificationHour = binding.dameTimePicker.hour
            NotificationWorker.notificationMinute = binding.dameTimePicker.minute
            delayCreateWork()
            dialog.dismiss()
        }

        binding.dameTimePicker.setIs24HourView(true)
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

    private fun delayCreateWork() {
        CoroutineScope(Dispatchers.Default).launch {
            createWorkManager()
        }
    }

    private fun createWorkManager() {

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>().setInitialDelay(
            getWaitingTimeBeforeNotification(
                notificationHour = NotificationWorker.notificationHour,
                notificationMinute = NotificationWorker.notificationMinute
            ),
            TimeUnit.MILLISECONDS
        ).build()

        Log.e(ContentValues.TAG, "Init WorkManager")
        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                NotificationWorker.WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                oneTimeWorkRequest
            )
    }
}