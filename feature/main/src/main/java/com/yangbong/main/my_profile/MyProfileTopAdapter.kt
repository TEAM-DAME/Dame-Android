package com.yangbong.main.my_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.core_ui.extension.navigateActivity
import com.yangbong.damedame.main.databinding.LayoutMyProfileTopBinding
import com.yangbong.damedame.notification.SettingActivity
import com.yangbong.domain.entity.MyProfileUser

class MyProfileTopAdapter : RecyclerView.Adapter<MyProfileTopAdapter.MyProfileTopViewHolder>() {
    var myProfileUser: MyProfileUser? = null
        set(value) {
            field = value
            notifyItemChanged(0)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyProfileTopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutMyProfileTopBinding.inflate(inflater, parent, false)
        return MyProfileTopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyProfileTopViewHolder, position: Int) {
        holder.onBind(myProfileUser)
    }

    override fun getItemCount() = 1

    class MyProfileTopViewHolder(
        private val binding: LayoutMyProfileTopBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(myProfileUser: MyProfileUser?) {
            myProfileUser?.let {
                binding.userData = it

                binding.btnSettings.setOnClickListener {
                    with(itemView.context) {
                        navigateActivity<SettingActivity>()
                    }
                }
            }
        }
    }
}
