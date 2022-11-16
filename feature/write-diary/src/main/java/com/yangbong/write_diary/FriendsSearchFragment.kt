package com.yangbong.write_diary

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.damedame.write_diary.databinding.FragmentFriendSearchBinding


class FriendsSearchFragment : Fragment() {
    lateinit var binding: FragmentFriendSearchBinding
    lateinit var myFSadapter:FriendSearchRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentFriendSearchBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }

    fun init(){
        val fragm=SearchResultFragment()
        binding.SearchBtn.setOnClickListener {
        //    parentFragmentManager.beginTransaction().replace(R.id.frag,fragm).commit()
        }
        var A = arrayListOf("이지환짱","지구야그럼목성")
        binding.FSRecyclerVIew.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        myFSadapter= FriendSearchRecyclerViewAdapter(A)

        myFSadapter.FSitemClickListner=object:FriendSearchRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
        //        parentFragmentManager.beginTransaction().replace(R.id.frag,fragm).commit()
            }
        }
        binding.FSRecyclerVIew.adapter=myFSadapter

    }


}