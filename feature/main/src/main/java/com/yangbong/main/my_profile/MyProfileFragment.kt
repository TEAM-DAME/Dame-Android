package com.yangbong.main.my_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.shortToast
import com.yangbong.core_ui.util.ItemDecorationUtil
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.core_ui.util.UiState
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentMyProfileBinding
import com.yangbong.domain.entity.MyProfileUser
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

@AndroidEntryPoint
class MyProfileFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentMyProfileBinding>(R.layout.fragment_my_profile) {
    private val viewModel: MyProfileViewModel by activityViewModels()
    private val myProfileTopAdapter = MyProfileTopAdapter(::onPocketClick, ::onFriendsClick)
    private val diaryAdapter = DiaryAdapter(::onLockClick, ::onDiaryClick)
    private val concatAdapter = ConcatAdapter(myProfileTopAdapter, diaryAdapter)
    private lateinit var navController: NavController

    private val Number.dp: Int
        get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController()
        initView()
        observeProfileInfo()
        observeDiaryList()
        viewModel.getMyProfileInfo()
        viewModel.getDiaryInfo()

    }

    private fun initNavController() {
        navController = findNavController()
    }

    private fun observeProfileInfo() {
        viewModel.myProfileUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    myProfileTopAdapter.myProfileUser = MyProfileUser(
                        userId = it.data.userId,
                        nickName = it.data.nickName,
                        profileImageUrl = it.data.profileImageUrl,
                        diaryCount = it.data.diaryCount,
                        minionCount = it.data.minionCount,
                        friendCount = it.data.friendCount
                    )
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

        viewModel.isDiaryEmpty.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            binding.isDiaryEmpty = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeDiaryList() {
        viewModel.diaryUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    diaryAdapter.submitList(it.data)
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

    private fun initView() {
        binding.rvMyProfile.apply {
            addItemDecoration(ItemDecorationUtil.VerticalPlaceItemDecoration(16.dp))
            adapter = concatAdapter
        }
    }

    private fun onLockClick(
        isLocked: Boolean = true,
        diaryId: Int
    ) {
//        LockBottomSheetDialog(isLocked, diaryId) { viewModel.getMyProfileInfo() }.show(
//            childFragmentManager,
//            this.tag
//        )
    }

    private fun onDiaryClick(id: Int) {
//        val intent = Intent(activity, DiaryDetailActivity::class.java).apply {
//            putExtra(DIARY_ID, id)
//        }
//        startActivity(intent)
    }

    private fun onPocketClick(id: Int) {
        navController.navigate(R.id.action_my_profile_navigation_to_pocket_navigation)
    }

    private fun onFriendsClick(id: Int) {
        navController.navigate(R.id.action_my_profile_navigation_to_friends_navigation)
    }

}
