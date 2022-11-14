package com.yangbong.domain.use_case.login

import com.yangbong.domain.repository.LoginRepository

class GetIsFirstVisited (
    private val repository: LoginRepository
) {

    operator fun invoke(): Boolean {
        return repository.getIsFirstVisited()
    }
}