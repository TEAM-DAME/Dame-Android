package com.yangbong.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentSearchProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchProfileFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentSearchProfileBinding>(R.layout.fragment_search_profile) {
    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }
    fun init(){
        binding.friendsOptionButton.setOnClickListener {
            if(binding.friendDeleteButton.isVisible)
                binding.friendDeleteButton.visibility=(View.INVISIBLE)
            else
                binding.friendDeleteButton.visibility=(View.GONE)
        }
    }
}