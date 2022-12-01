package com.yangbong.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Observer

@AndroidEntryPoint
class SearchFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val searchViewModel: SearchViewModel by activityViewModels()
    lateinit var myFSadapter: FriendSearchRecyclerViewAdapter
    lateinit var searchData:ArrayList<String>
    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        binding.searchViewModel=searchViewModel
        super.onViewCreated(view, savedInstanceState)
    }
    fun init(){


        searchData= arrayListOf()
        binding.FSRecyclerVIew.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        myFSadapter= FriendSearchRecyclerViewAdapter(searchData)
        myFSadapter.FSitemClickListner=object :FriendSearchRecyclerViewAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                searchViewModel.recentOnClick()
            }

            override fun onXClick(position: Int) {
                var data=myFSadapter.items[position]
                searchViewModel.deleteData(data)
            }
        }
        binding.FSRecyclerVIew.adapter=myFSadapter
        binding.deleteAll.setOnClickListener {
            searchViewModel.deleteAllData()

        }
        binding.SearchBtn.setOnClickListener {
            searchViewModel.addSearchData(binding.SearchTxt.text.toString())
            searchViewModel.searchOnClick()
            val intent=Intent(activity,SearchActivity::class.java)
            startActivity(intent)
        }
        searchViewModel.searchdata.observe(viewLifecycleOwner,androidx.lifecycle.Observer{
            myFSadapter.items= searchViewModel.searchdata.value!!
            (binding.FSRecyclerVIew.adapter)?.notifyDataSetChanged()

        }

        )

    }
}
