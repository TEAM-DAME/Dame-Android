package com.yangbong.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.yangbong.core_ui.base.BindingActivity
import com.yangbong.core_ui.util.ResolutionMetrics
import com.yangbong.damedame.main.R
import com.yangbong.damedame.main.databinding.ActivitySearchBinding
import com.yangbong.damedame.main.databinding.FragmentMyProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class SearchActivity :
    BindingActivity<ActivitySearchBinding>(R.layout.activity_search){

    @Inject
    lateinit var resolutionMetrics: ResolutionMetrics
    override fun onCreate(savedInstanceState: Bundle?) {
        val frag2=SearchResultFragment(resolutionMetrics)
        val fragmentM=supportFragmentManager
        fragmentM.beginTransaction().replace(R.id.frag_contatiner,frag2).commit()
        super.onCreate(savedInstanceState)

    }
}