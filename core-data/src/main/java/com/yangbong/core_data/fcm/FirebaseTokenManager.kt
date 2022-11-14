package com.yangbong.core_data.fcm

interface FirebaseTokenManager {
    fun getFirebaseToken(tokenCallBack: (String) -> Unit)
}

