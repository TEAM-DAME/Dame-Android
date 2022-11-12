package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class SaveAccessToken(
    private val repository: LoginRepository
) {

    operator fun invoke(accessToken: String) {
        repository.saveAccessToken(accessToken)
    }
}