package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class GetAccessToken(
    private val repository: LoginRepository
) {

    operator fun invoke(): String {
        return repository.getAccessToken()
    }
}