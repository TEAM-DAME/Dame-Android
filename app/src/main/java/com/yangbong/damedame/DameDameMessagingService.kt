package com.yangbong.damedame

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class DameDameMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        // 서버에게 토큰 새로 보내주는 코드
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // TODO(채널 생성 등 로직 추가)
        print("fortest")
        print("fortest2")
    }


}
