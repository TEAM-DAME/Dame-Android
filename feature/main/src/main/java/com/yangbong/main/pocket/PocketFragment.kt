package com.yangbong.main.pocket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.extension.shortToast
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.core_ui.util.UiState
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentPocketBinding
import com.yangbong.set_character.ui.SetCharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PocketFragment :
    BindingFragment<FragmentPocketBinding>(R.layout.fragment_pocket) {
    private val pocketViewModel: PocketViewModel by activityViewModels()
    private lateinit var characterAdapter: SetCharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nickname = arguments?.getString("nickname")
        pocketViewModel.getCharacterList(arguments?.getInt("userId") ?: -1)
        observeCharacterList()
        initButtonClickListener()
    }

    private fun observeCharacterList() {
        pocketViewModel.pocketUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    characterAdapter.submitList(it.data)
                }
                is UiState.Failure -> {
                    it.msg?.let { msg ->
                        requireContext().shortToast(msg)
                    }
                }
                else -> {
                    // TODO : 로딩 로직
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initButtonClickListener() {
        binding.btnBack.setOnSingleClickListener {
            findNavController().navigateUp()
        }
    }

}