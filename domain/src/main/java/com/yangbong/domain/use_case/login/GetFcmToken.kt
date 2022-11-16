package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class GetFcmToken(
    private val repository: LoginRepository
) {

    operator fun invoke(tokenCallBack: (String) -> Unit) {
        repository.getFcmToken(tokenCallBack)
    }
}