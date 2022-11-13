package com.yangbong.domain.use_case.login

import com.yangbong.domain.entity.request.DomainLoginRequest
import com.yangbong.domain.entity.response.DomainLoginResponse
import com.yangbong.domain.repository.LoginRepository

class PostLogin (
    private val repository: LoginRepository
) {

    suspend operator fun invoke(loginRequest: DomainLoginRequest): Result<DomainLoginResponse> {
        return repository.postLogin(loginRequest)
    }
}