package com.yangbong.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.util.Event
import com.yangbong.core_ui.util.EventFlow
import com.yangbong.core_ui.util.MutableEventFlow
import com.yangbong.core_ui.util.asEventFlow
import com.yangbong.domain.use_case.login.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    private val _socialToken = MutableLiveData<String>()
    val socialToken: LiveData<String> = _socialToken

    private lateinit var platform: String

    private val _navigateToHome = MutableLiveData<Event<Boolean>>()
    val navigateToHome: LiveData<Event<Boolean>> = _navigateToHome

    fun getAccessToken(): String =
        loginUseCases.getAccessToken()

    fun updateSocialToken(socialToken: String) {
        _socialToken.value = socialToken
    }

    fun updatePlatform(platform: String) {
        this.platform = platform
    }
}