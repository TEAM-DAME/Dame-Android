package com.yangbong.set_profile

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.damedame.set_profile.databinding.FragmentSelectCharacterBinding
import com.yangbong.damedame.set_profile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCharacterFragment : BindingFragment<FragmentSelectCharacterBinding>(R.layout.fragment_select_character) {
    private val selectCharacterViewModel by viewModels<SelectCharacterViewModel>()

    lateinit var adapter: SelectCharacterAdapter
    val characterData: ArrayList<CharacterData> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initData()
    }
    private fun initRecyclerView() {
        adapter = SelectCharacterAdapter(characterData)
        adapter.itemClickListener = object : SelectCharacterAdapter.OnItemClickListener
        {
            override fun OnItemClick(data: CharacterData, imageView: ImageView) {
                //팝업 화면 전환
            }

        }
        binding.rvSelectCharacter.adapter = adapter

    }

    private fun initData() {
        //TODO:: 이미지 서버에서 가져와서 characterData에 넣기 -> CharacterData 구조 변경 필요

        characterData.add(CharacterData("구름이"))
        characterData.add(CharacterData("몽실이"))
        characterData.add(CharacterData("솜사탕"))
        characterData.add(CharacterData("개나리"))
        characterData.add(CharacterData("까망이"))
        characterData.add(CharacterData("토순이"))
        characterData.add(CharacterData("뿡뿡이"))
        characterData.add(CharacterData("째깐이"))
        characterData.add(CharacterData("팬둥이"))
    }

    private fun getImageData(){

    }


}