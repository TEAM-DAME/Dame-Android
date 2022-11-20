package com.yangbong.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.damedame.main.databinding.FragmentSearchResultBinding


class SearchResultFragment : Fragment() {
    lateinit var binding: FragmentSearchResultBinding
    lateinit var mySRadapter: SearchResultRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchResultBinding.inflate(inflater,container,false)
        init()
        return binding.root
    }
    fun init(){
        var A= arrayListOf(SearchData("dd","dd"), SearchData("bb","bb")) //TODO GLide 이용해서 url 데이터로
        binding.RSRecyclerView.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mySRadapter= SearchResultRecyclerViewAdapter(A)
        mySRadapter.SRitemClickListener=object :
            SearchResultRecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                super.onItemClick(position) // 이제 해당 프로필로 넘어감
            }
        }

        binding.RSRecyclerView.adapter=mySRadapter
    }



}