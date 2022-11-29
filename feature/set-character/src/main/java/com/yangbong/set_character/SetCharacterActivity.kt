package com.yangbong.set_character

import android.os.Bundle
import androidx.activity.viewModels
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.damedame.set_character.databinding.ActivitySetCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import com.yangbong.damedame.set_character.R

@AndroidEntryPoint
class SetCharacterActivity :
    BindingActivity<ActivitySetCharacterBinding>(R.layout.activity_set_character) {
        private val setCharacterViewModel by viewModels<SetCharacterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}