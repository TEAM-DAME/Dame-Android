package com.yangbong.main.friends

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentFriendsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private val viewModel: FriendsViewModel by activityViewModels()

    private val kotlin.Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nickname = arguments?.getString("nickname")
        initButtonClickListener()
    }

    private fun initButtonClickListener() {
        binding.btnBack.setOnSingleClickListener {
            findNavController().navigateUp()
        }
    }

}