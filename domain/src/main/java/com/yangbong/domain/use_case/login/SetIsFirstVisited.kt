package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class SetIsFirstVisited(
    private val repository: LoginRepository
) {

    operator fun invoke(isFirstVisit: Boolean) {
        repository.setIsFirstVisited(isFirstVisit)
    }
}