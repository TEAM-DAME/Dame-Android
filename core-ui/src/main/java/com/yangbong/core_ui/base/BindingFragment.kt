package com.yangbong.core_ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.yangbong.core_ui.util.Injector
import com.yangbong.damedame.navigator.MainNavigator
import dagger.hilt.android.EntryPointAccessors

abstract class BindingFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    val mainNavigator: MainNavigator by lazy {
        EntryPointAccessors.fromActivity(
            requireActivity(),
            Injector.MainNavigatorInjector::class.java
        ).mainNavigator()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
