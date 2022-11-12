package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class SaveUserProfileImageUrl(
    private val repository: LoginRepository
) {

    operator fun invoke(userProfileImageUrl: String) {
        repository.saveUserProfileImageUrl(userProfileImageUrl)
    }
}