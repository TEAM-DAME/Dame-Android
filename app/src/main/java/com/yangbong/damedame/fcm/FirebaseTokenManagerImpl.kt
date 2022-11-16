package com.yangbong.damedame.fcm

import com.google.firebase.messaging.FirebaseMessaging
import com.yangbong.core_data.fcm.FirebaseTokenManager
import timber.log.Timber
import javax.inject.Inject

class FirebaseTokenManagerImpl @Inject constructor() : FirebaseTokenManager {
    override fun getFirebaseToken(tokenCallBack: (String) -> Unit) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                tokenCallBack(task.result)
            } else {
                Timber.d("fcm Token 오류 : ${task.exception}")
            }
        }
    }
}
