package com.yangbong.set_character.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.util.EventObserver
import com.yangbong.damedame.set_character.databinding.ActivitySetCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import com.yangbong.damedame.set_character.R
import com.yangbong.set_character.view.CharacterInfoBottomSheetDialog

@AndroidEntryPoint
class SetCharacterActivity :
    BindingActivity<ActivitySetCharacterBinding>(R.layout.activity_set_character) {
    private val setCharacterViewModel by viewModels<SetCharacterViewModel>()
    private lateinit var setCharacterAdapter: SetCharacterAdapter
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCharacterViewModel.getCharacterList()
        initExtraData()
        initAdapter()
        updateRecyclerViewList()
        initMoveToHomeObserver()
    }

    private fun initExtraData() {
        userId = intent.getIntExtra("userId", -1)
    }

    private fun initAdapter() {
        setCharacterAdapter = SetCharacterAdapter(::onCharacterClick)
        binding.rvSelectCharacter.adapter = setCharacterAdapter
    }

    private fun onCharacterClick(characterId: Int) {
        CharacterInfoBottomSheetDialog(characterId, ::onSelectClick).show(
            supportFragmentManager,
            this.javaClass.name
        )
    }

    private fun updateRecyclerViewList() {
        setCharacterViewModel.characterInfoList.observe(this) {
            setCharacterAdapter.submitList(it)
        }
    }

    private fun onSelectClick(characterId: Int) {
        setCharacterViewModel.postCharacter(userId, characterId)
    }

    private fun initMoveToHomeObserver() {
        setCharacterViewModel.navigateToHome.observe(
            this,
            EventObserver {
                navigateMainActivity()
            }
        )
    }

    private fun navigateMainActivity() {
        mainNavigator.navigateMain(this)
        finish()
    }
}
