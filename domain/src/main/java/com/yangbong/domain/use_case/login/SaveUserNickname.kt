package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class SaveUserNickname(
    private val repository: LoginRepository
) {

    operator fun invoke(userNickname: String) {
        repository.saveUserNickname(userNickname)
    }
}