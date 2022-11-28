package com.yangbong.main.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    lateinit var myFSadapter: FriendSearchRecyclerViewAdapter
    private val viewModel: SearchViewModel by activityViewModels()
    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }
    fun init(){
        val fragm= SearchResultFragment()

        binding.SearchBtn.setOnClickListener {
            //    parentFragmentManager.beginTransaction().replace(R.id.frag,fragm).commit()
        }
        var A = arrayListOf("이지환짱","지구야그럼목성")
        binding.FSRecyclerVIew.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        myFSadapter= FriendSearchRecyclerViewAdapter(A)

        myFSadapter.FSitemClickListner=object: FriendSearchRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                //        parentFragmentManager.beginTransaction().replace(R.id.frag,fragm).commit()
            }
        }
        binding.FSRecyclerVIew.adapter=myFSadapter
        binding.deleteAll.setOnClickListener {
            myFSadapter.items.clear()
            myFSadapter.notifyDataSetChanged()
        }

    }

}
