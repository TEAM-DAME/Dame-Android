package com.yangbong.main.friends

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController

import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.extension.shortToast
import com.yangbong.core_ui.util.ItemDecorationUtil
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.core_ui.util.UiState
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentFriendsBinding
import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.entity.SearchInfo
import com.yangbong.main.my_profile.DiaryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FriendsFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private val friendsViewModel: FriendsViewModel by activityViewModels()
    private val friendsAdapter = FriendsAdapter(::onFriendClick)

    private val Number.dp: Int
        get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nickname = arguments?.getString("nickname")
        friendsViewModel.getFriendList(arguments?.getInt("userId") ?: -1)
        initView()
        initButtonClickListener()
        observeFriendsList()
    }

    private fun initButtonClickListener() {
        binding.btnBack.setOnSingleClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initView() {
        binding.rvFriendsList.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = friendsAdapter
        }
    }

    private fun observeFriendsList() {
        friendsViewModel.friendsUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    friendsAdapter.submitList(it.data)
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

        friendsViewModel.isFriendEmpty.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            binding.isFriendEmpty = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onFriendClick(userId: Int) {
        // TODO("친구 프로필 넘어가는 로직")
    }
}