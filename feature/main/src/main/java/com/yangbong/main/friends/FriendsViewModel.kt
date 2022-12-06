package com.yangbong.main.friends

import androidx.lifecycle.viewModelScope
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.core_ui.util.EventFlow
import com.yangbong.core_ui.util.MutableEventFlow
import com.yangbong.core_ui.util.UiState
import com.yangbong.core_ui.util.asEventFlow
import com.yangbong.domain.entity.CharacterInfo
import com.yangbong.domain.entity.FriendProfileInfo
import com.yangbong.domain.repository.FriendsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel @Inject constructor(
    private val friendsRepository: FriendsRepository
) : BaseViewModel() {

    private val _isFriendEmpty = MutableEventFlow<Boolean>()
    val isFriendEmpty: EventFlow<Boolean>
        get() = _isFriendEmpty.asEventFlow()

    private val _friendsUiState: MutableStateFlow<UiState<List<FriendProfileInfo>>> =
        MutableStateFlow(UiState.Loading)
    val friendsUiState: StateFlow<UiState<List<FriendProfileInfo>>>
        get() = _friendsUiState.asStateFlow()

    fun getFriendList(userId: Int) {
        viewModelScope.launch {
            _friendsUiState.value = UiState.Loading
            friendsRepository.getFriendList(
                userId = userId,
                page = 1,
                size = 20
            )
                .onSuccess {
                    _friendsUiState.value = UiState.Success(it)
                    _isFriendEmpty.emit(it.isEmpty())
                    Timber.tag("okhttp").d("getFriendList SUCCESS!")
                }
                .onFailure {
                    _friendsUiState.value = UiState.Failure(it.message)
                    Timber.tag("okhttp").d("getFriendList Failure!")
                }
        }
    }
}