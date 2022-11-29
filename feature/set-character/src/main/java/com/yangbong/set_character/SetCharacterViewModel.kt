package com.yangbong.set_character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yangbong.core_ui.base.BaseViewModel
import com.yangbong.domain.repository.SetCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SetCharacterViewModel @Inject constructor(
    private val setCharacterRepository: SetCharacterRepository
) : BaseViewModel() {
    private val _minionId = MutableLiveData<Int>()
    val minionId: LiveData<Int> = _minionId

    
}