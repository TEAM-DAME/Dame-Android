package com.yangbong.main.friends

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController

import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentFriendsBinding
import com.yangbong.domain.entity.ProfileInfo
import com.yangbong.domain.entity.SearchInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FriendsFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentFriendsBinding>(R.layout.fragment_friends) {
    private val friendViewModel: FriendsViewModel by activityViewModels()
    lateinit var myFrAdapter:FriendsRecyclerViewAdapter
    lateinit var clickedUserData: ProfileInfo

    private val kotlin.Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        friendViewModel.getUserNickName()
        friendViewModel.getUserId()
        init()

        binding.nickname = arguments?.getString("nickname")
        initButtonClickListener()
    }

    private fun initButtonClickListener() {
        binding.btnBack.setOnSingleClickListener {
            findNavController().navigateUp()
        }

    }


    fun init(){
        val friendData= arrayListOf<SearchInfo>()
        binding.rvFriendsList.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        myFrAdapter= FriendsRecyclerViewAdapter(friendData)
        myFrAdapter.friendItemClickListener=object :FriendsRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                friendViewModel.getUserProfileInfo(myFrAdapter.items[position].userId)
                clickedUserData=friendViewModel.userProfileData.value!!
                //clickedUserData 에 프로필뜨게할 유저정보 저장 .
            }
        }
        binding.rvFriendsList.adapter=myFrAdapter
        friendViewModel.getFriendList(friendViewModel.userId.value!!,1,20)
        friendViewModel.friendListData.observe(viewLifecycleOwner, Observer{
            myFrAdapter.items=friendViewModel.friendListData.value!!
            (binding.rvFriendsList.adapter)?.notifyDataSetChanged()
        })
    }
}