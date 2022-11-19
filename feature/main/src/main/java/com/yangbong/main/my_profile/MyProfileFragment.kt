package com.yangbong.main.my_profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.shortToast
import com.yangbong.core_ui.util.ItemDecorationUtil
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.core_ui.util.UiState
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentMyProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyProfileFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentMyProfileBinding>(R.layout.fragment_my_profile) {
    private val viewModel: MyProfileViewModel by viewModels()
    private val myProfileTopAdapter = MyProfileTopAdapter()
    private val diaryAdapter = DiaryAdapter(::onLockClick, ::onDiaryClick)
    private val concatAdapter = ConcatAdapter(myProfileTopAdapter, diaryAdapter)

    private val Number.dp: Int
        get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        terminationTokenHandling(viewModel)
        initView()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMyProfileInfo()
    }

    private fun observeData() {
        viewModel.myProfileUiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).onEach {
            when (it) {
                is UiState.Success -> {
                    myProfileTopAdapter.myProfileUser = it.data.myProfileUser
                    diaryAdapter.submitList(it.data.diaryList)
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

}
