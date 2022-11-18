package com.yangbong.main.my_profile

import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.util.EventFlow
import com.yangbong.core_ui.util.MutableEventFlow
import com.yangbong.core_ui.util.UiState
import com.yangbong.core_ui.util.asEventFlow
import com.yangbong.domain.entity.MyProfileInfo
import com.yangbong.domain.repository.MyProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val myProfileRepository: MyProfileRepository
) : BaseViewModel() {

    private val _isDiaryEmpty = MutableEventFlow<Boolean>()
    val isDiaryEmpty: EventFlow<Boolean>
        get() = _isDiaryEmpty.asEventFlow()

    private val _myProfileUiState: MutableStateFlow<UiState<MyProfileInfo>> =
        MutableStateFlow(UiState.Loading)
    val myProfileUiState: StateFlow<UiState<MyProfileInfo>>
        get() = _myProfileUiState.asStateFlow()

    fun getMyProfileInfo() {
        viewModelScope.launch(exceptionHandler) {
            _myProfileUiState.value = UiState.Loading
            myProfileRepository.getMyProfileInfo()
                .onSuccess {
                    _myProfileUiState.value = UiState.Success(it)
                    _isDiaryEmpty.emit(it.diaryList.isEmpty())
                }
                .onFailure {
                    _myProfileUiState.value = UiState.Failure(it.message)
                }
        }
    }
}