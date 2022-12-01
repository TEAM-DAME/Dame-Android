package com.yangbong.set_character.ui

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.util.EventObserver
import com.yangbong.damedame.set_character.databinding.ActivitySetCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import com.yangbong.damedame.set_character.R
import com.yangbong.set_character.CharacterData
import com.yangbong.set_character.view.CharacterInfoBottomSheetDialog
import com.yangbong.set_character.SetCharacterAdapter

@AndroidEntryPoint
class SetCharacterActivity :
    BindingActivity<ActivitySetCharacterBinding>(R.layout.activity_set_character) {
    private val setCharacterViewModel by viewModels<SetCharacterViewModel>()

    lateinit var adapter: SetCharacterAdapter

    val characterData: ArrayList<CharacterData> = ArrayList()

//    lateinit var userId: Int
//    lateinit var minion: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = SetCharacterAdapter(characterData)
        adapter.itemClickListener = object : SetCharacterAdapter.OnItemClickListener {
            override fun OnItemClick(data: CharacterData, imageView: ImageView) {
                CharacterInfoBottomSheetDialog(
                    onSelectClick = ::postCharacterInfo
                ).show(
                    supportFragmentManager,
                    this.javaClass.name
                )
            }
        }
        binding.rvSelectCharacter.adapter = adapter
    }
    private fun postCharacterInfo(){
//        setCharacterViewModel.postCharacter(minion,userId)
        navigateMainActivity()
    }

    private fun navigateMainActivity() {
        mainNavigator.navigateMain(this)
        finish()
    }

    private fun initNavigateToMainActivityObserver(){
        setCharacterViewModel.navigateToMainActivity.observe(
            this,
            EventObserver{
                navigateMainActivity()
            }
        )
    }

    private fun initData() {
        /* TODO:: 이미지 서버에서 가져와서 characterData에 넣기 -> CharacterData 구조 변경 필요
        selectCharacterViewModel.getMinions()
        for(i in 0 until selectCharacterViewModel.allMinions.size){
            characterData.add(CharacterData(selectCharacterViewModel.allMinions[i]));
        }
        */

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

}
