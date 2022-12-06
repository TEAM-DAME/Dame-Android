package com.yangbong.main.friends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.ItemDiffCallback
import com.yangbong.damedame.main.databinding.ItemFriendBinding
import com.yangbong.domain.entity.FriendProfileInfo

class FriendsAdapter(
    private val onFriendClick: (Int) -> Unit
) : ListAdapter<FriendProfileInfo, FriendsAdapter.FriendsViewHolder>(
    ItemDiffCallback<FriendProfileInfo>(
        onContentsTheSame = { oldItem, newItem -> oldItem.hashCode() == newItem.hashCode() },
        onItemsTheSame = { oldItem, newItem -> oldItem.userId == newItem.userId }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)
        val binding = ItemFriendBinding.inflate(inflater, parent, false)
        return FriendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FriendsViewHolder, position: Int) {
        holder.onBind(getItem(position), onFriendClick)
    }

    class FriendsViewHolder(private val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            friendProfileInfo: FriendProfileInfo,
            onFriendClick: (Int) -> Unit
        ) {
            binding.root.setOnSingleClickListener { onFriendClick(friendProfileInfo.userId ?: -1) }
        }
    }
}