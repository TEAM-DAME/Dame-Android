package com.yangbong.set_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.damedame.set_profile.databinding.FragmentSelectCharacterBinding
import com.yangbong.damedame.set_profile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCharacterFragment : BindingFragment<FragmentSelectCharacterBinding>(R.layout.fragment_select_character) {
    private val selectCharacterViewModel by viewModels<SelectCharacterViewModel>()

    lateinit var adapter: SelectCharacterAdapter
    val characterData: ArrayList<SelectCharacterData> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initRecyclerView()
        initImgClickListener()
    }

    private fun initImgClickListener() {

    }

    private fun initRecyclerView() {
        adapter = SelectCharacterAdapter(characterData)
        binding.rvSelectCharacter.adapter = adapter
    }

    private fun initData() {
        characterData.add(SelectCharacterData("구름이"))
        characterData.add(SelectCharacterData("몽실이"))
        characterData.add(SelectCharacterData("솜사탕"))
        characterData.add(SelectCharacterData("개나리"))
        characterData.add(SelectCharacterData("까망이"))
        characterData.add(SelectCharacterData("토순이"))
        characterData.add(SelectCharacterData("뿡뿡이"))
        characterData.add(SelectCharacterData("째깐이"))
        characterData.add(SelectCharacterData("팬둥이"))
    }


}