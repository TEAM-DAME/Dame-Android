package com.yangbong.main.pocket

import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.util.UiState
import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.repository.PocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PocketViewModel @Inject constructor(
    private val pocketRepository: PocketRepository
) : BaseViewModel() {

    private val _pocketUiState: MutableStateFlow<UiState<List<CharacterInfo>>> =
        MutableStateFlow(UiState.Loading)
    val pocketUiState: StateFlow<UiState<List<CharacterInfo>>>
        get() = _pocketUiState.asStateFlow()

    fun getCharacterList(userId: Int) {
        viewModelScope.launch {
            _pocketUiState.value = UiState.Loading
            pocketRepository.getCharacterList(
                userId = userId
            )
                .onSuccess {
                    _pocketUiState.value = UiState.Success(it)
                    Timber.tag("okhttp").d("getCharacterList SUCCESS!")
                }
                .onFailure {
                    _pocketUiState.value = UiState.Failure(it.message)
                    Timber.tag("okhttp").d("getCharacterList Failure!")
                }
        }
    }

}