package com.yangbong.main.pocket

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentPocketBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PocketFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentPocketBinding>(R.layout.fragment_pocket) {
    private val viewModel: PocketViewModel by activityViewModels()

    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}