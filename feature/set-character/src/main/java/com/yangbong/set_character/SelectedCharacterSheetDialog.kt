package com.yangbong.set_character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yangbong.damedame.shared.databinding.LayoutSelectedCharacterSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yangbong.damedame.shared.R

class SelectedCharacterSheetDialog(
    private val onSelectClick: () -> Unit,
) :BottomSheetDialogFragment() {
    private var _binding: LayoutSelectedCharacterSheetBinding? = null
    protected val binding: LayoutSelectedCharacterSheetBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            DataBindingUtil.inflate(inflater,R.layout.layout_selected_character_sheet,container,false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSelectClickListener()
    }

    private fun onSelectClickListener() {
        binding.selectBtn.setOnClickListener {
            onSelectClick.invoke()
            dismiss()
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}