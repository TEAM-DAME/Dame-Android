package com.yangbong.main.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel: ProfileViewModel by activityViewModels()

    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    // test
}
