package com.yangbong.damedame.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yangbong.damedame.settings.databinding.FragmentNotificationBinding


class NotificationFragment : Fragment() {
    lateinit var adapter: NotificationRecyclerViewAdapter
    var binding: FragmentNotificationBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=NotificationRecyclerViewAdapter(
            listOf(1, 2, 3)
        )
        binding!!.notificationRecycler.layoutManager=
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        binding!!.notificationRecycler.adapter=adapter
    }
}
