package com.yangbong.set_profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.damedame.set_profile.databinding.FragmentSelectCharacterBinding
import com.yangbong.damedame.set_profile.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectCharacterFragment : BindingFragment<FragmentSelectCharacterBinding>(R.layout.fragment_select_character) {

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
                //TODO:: 캐릭터 정보(캐릭터 id, 해당 캐릭터 설명) 넘겨주어 해당 캐릭터 팝업창으로 띄우기 필요.
                // 현재 구름이만 뜨도록 되어 있음
                val characterInfoFragment = CharacterInfoFragment()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.infoCharacterFramLayout, characterInfoFragment).commit()
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