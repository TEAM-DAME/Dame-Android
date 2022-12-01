package com.yangbong.set_character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yangbong.damedame.set_character.R
import com.yangbong.damedame.set_character.databinding.LayoutCharacterInfoBottomSheetBinding

class CharacterInfoBottomSheetDialog(
    private val characterId: Int,
    private val onSelectClick: (Int) -> Unit
) : BottomSheetDialogFragment() {
    private var _binding: LayoutCharacterInfoBottomSheetBinding? = null
    protected val binding: LayoutCharacterInfoBottomSheetBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.layout_character_info_bottom_sheet,
                container,
                false
            )
        binding.lifecycleOwner = viewLifecycleOwner
        binding.characterId = characterId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSelectClickListener()
    }

    private fun onSelectClickListener() {
        binding.btnSelect.setOnClickListener {
            onSelectClick(characterId)
            dismiss()
        }
    }

    override fun getTheme(): Int = com.yangbong.damedame.shared.R.style.AppBottomSheetDialogTheme

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}