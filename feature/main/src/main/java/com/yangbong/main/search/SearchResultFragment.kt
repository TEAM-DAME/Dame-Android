package com.yangbong.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentSearchBinding
import com.yangbong.damedame.main.databinding.FragmentSearchResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchResultFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result){
    lateinit var mySRadapter: SearchResultRecyclerViewAdapter
    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        super.onViewCreated(view, savedInstanceState)
    }

    fun init(){
        val frag1=SearchFragment(resolutionMetrics)
        val frag3=SearchProfileFragment(resolutionMetrics)
        var A= arrayListOf(SearchData("dd","dd"), SearchData("bb","bb")) //TODO GLide 이용해서 url 데이터로
        binding.RSRecyclerView.layoutManager=
            LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mySRadapter= SearchResultRecyclerViewAdapter(A)
        mySRadapter.SRitemClickListener=object :
            SearchResultRecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                super.onItemClick(position) // 이제 해당 프로필로 넘어감
                parentFragmentManager.beginTransaction().replace(R.id.frag_contatiner,frag3).commit()
            }
        }

        binding.RSRecyclerView.adapter=mySRadapter
        binding.backBtn.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.remove(this)?.commit()
        }
    }



}