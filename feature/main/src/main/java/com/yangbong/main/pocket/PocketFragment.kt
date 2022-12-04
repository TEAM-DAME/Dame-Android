package com.yangbong.main.pocket

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.yangbong.core_ui.base.BindingFragment
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.FragmentPocketBinding
import com.yangbong.domain.entity.CharacterInfo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PocketFragment(private val resolutionMetrics: ResolutionMetrics) :
    BindingFragment<FragmentPocketBinding>(R.layout.fragment_pocket) {
    private val pocketViewModel: PocketViewModel by activityViewModels()
    lateinit var myPAdapter:PocketRecyclerViewAdapter


    private val Number.dp get() = resolutionMetrics.toPixel(this.toInt())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){
        val characterData= arrayListOf<CharacterInfo>()
        binding.rvCharacters.layoutManager=
            GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        myPAdapter=PocketRecyclerViewAdapter(characterData)
        binding.rvCharacters.adapter=myPAdapter
        pocketViewModel.getUserPocket(pocketViewModel.userId.value!!)
        pocketViewModel.characterList.observe(viewLifecycleOwner,Observer{
            if(pocketViewModel.characterList.value.isNullOrEmpty()){
                Log.i("message","도감이 비었음")
                myPAdapter.items=characterData
                (binding.rvCharacters.adapter)?.notifyDataSetChanged()
            }
            else{
                val convertData=arrayListOf<CharacterInfo>()
                for(i in 0..pocketViewModel.characterList.value!!.size){
                    convertData[i].characterId=pocketViewModel.characterList.value!![i]
                    myPAdapter.items=convertData
                    (binding.rvCharacters.adapter)?.notifyDataSetChanged()
                }
            }

        })
    }
}