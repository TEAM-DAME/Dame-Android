package com.yangbong.set_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.damedame.set_profile.databinding.FragmentCharacterInfoBinding
import com.yangbong.damedame.set_profile.R

class CharacterInfoFragment : BindingFragment<FragmentCharacterInfoBinding>(R.layout.fragment_character_info) {
    private val selectCharacterViewModel by viewModels<SelectCharacterViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCharacter()
        initSelectButtonClickListener()
    }

    private fun initCharacter() {
        //TODO:: 캐릭터 id 값 받아와 minion 값 변경 필요.
//        selectCharacterViewModel.minion.value = 1
    }

    private fun initSelectButtonClickListener() {
        binding.selectBtn.setOnClickListener {
            selectCharacterViewModel.putInitCharacter()
        }
    }
}