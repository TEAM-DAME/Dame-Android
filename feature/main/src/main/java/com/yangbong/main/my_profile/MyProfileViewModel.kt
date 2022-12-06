package com.yangbong.main.my_profile

import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.util.EventFlow
import com.yangbong.core_ui.util.MutableEventFlow
import com.yangbong.core_ui.util.UiState
import com.yangbong.core_ui.util.asEventFlow
import com.yangbong.domain.entity.DiaryInfo
import com.yangbong.domain.entity.MyProfileInfo
import com.yangbong.domain.repository.MyProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
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

    private val _diaryUiState: MutableStateFlow<UiState<List<DiaryInfo>>> =
        MutableStateFlow(UiState.Loading)
    val diaryUiState: StateFlow<UiState<List<DiaryInfo>>>
        get() = _diaryUiState.asStateFlow()

    fun getMyProfileInfo() {
        viewModelScope.launch {
            _myProfileUiState.value = UiState.Loading
            myProfileRepository.getMyProfileInfo(myProfileRepository.getUserId())
                .onSuccess {
                    _myProfileUiState.value = UiState.Success(it)
                    Timber.tag("okhttp").d("getMyProfileInfo SUCCESS!")
                }
                .onFailure {
                    _myProfileUiState.value = UiState.Failure(it.message)
                    Timber.tag("okhttp").d("getMyProfileInfo Failure!")
                }
        }
    }

    fun getDiaryInfo() {
        viewModelScope.launch {
            _diaryUiState.value = UiState.Loading
            myProfileRepository.getDiaryList(
                userId = myProfileRepository.getUserId(),
                page = 1,
                size = 20
            )
                .onSuccess {
                    _diaryUiState.value = UiState.Success(it)
                    _isDiaryEmpty.emit(it.isEmpty())
                    Timber.tag("okhttp").d("getDiaryInfo SUCCESS!")
                }
                .onFailure {
                    _diaryUiState.value = UiState.Failure(it.message)
                    Timber.tag("okhttp").d("getDiaryInfo Failure!")
                }
        }
    }

}