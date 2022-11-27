package com.yangbong.set_profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.damedame.shared.R
import com.yangbong.damedame.shared.databinding.LayoutImageBottomSheetBinding

class ImageBottomSheetDialog(
    private val onGalleryClick: () -> Unit,
    private val onCameraClick: () -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: LayoutImageBottomSheetBinding? = null
    protected val binding: LayoutImageBottomSheetBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.layout_image_bottom_sheet, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onTvActionClickListener()
    }

    private fun onTvActionClickListener() {
        binding.tvSelectImageFromGallery.setOnSingleClickListener {
            onGalleryClick.invoke()
            dismiss()
        }
        binding.tvSelectImageFromCamera.setOnSingleClickListener {
            onCameraClick.invoke()
            dismiss()
        }
    }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}