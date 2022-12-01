package com.yangbong.data.remote.data_source

import com.yangbong.data.remote.call_adapter.NetworkState
import com.yangbong.data.remote.model.request.LoginRequest
import com.yangbong.data.remote.model.response.BaseResponse
import com.yangbong.data.remote.model.response.LoginResponse


interface RemoteLoginDataSource {

    suspend fun postLogin(loginRequest: LoginRequest): NetworkState<BaseResponse<LoginResponse?>>
}
