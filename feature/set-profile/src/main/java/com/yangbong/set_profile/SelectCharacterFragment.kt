package com.yangbong.set_profile

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ImageView
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
        initRecyclerView()
        initData()
    }
    private fun initRecyclerView() {
        adapter = SelectCharacterAdapter(characterData)
        adapter.itemClickListener = object : SelectCharacterAdapter.OnItemClickListener
        {
            override fun OnItemClick(data: SelectCharacterData, imageView: ImageView) {
                //팝업 화면 전환
            }

        }
        binding.rvSelectCharacter.adapter = adapter

    }



    private fun initData() {
        //TODO:: 이미지 서버에서 가져와서 characterData에 넣기 -> 데이터 구조 변경 필요

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

    private fun getImageData(){

    }


}