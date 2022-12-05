package com.yangbong.set_character.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.ItemDiffCallback
import com.yangbong.damedame.set_character.databinding.ItemCharacterBinding
import com.yangbong.domain.entity.CharacterInfo

class SetCharacterAdapter(
    private val onCharacterClick: (Int) -> Unit
) : ListAdapter<CharacterInfo, SetCharacterAdapter.CharacterInfoViewHolder>(
    ItemDiffCallback<CharacterInfo>(
        onContentsTheSame = { oldItem, newItem -> oldItem.hashCode() == newItem.hashCode() },
        onItemsTheSame = { oldItem, newItem -> oldItem.minionId == newItem.minionId }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterInfoViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterInfoViewHolder, position: Int) {
        holder.onBind(getItem(position), onCharacterClick)
    }

    class CharacterInfoViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            characterInfo: CharacterInfo,
            onCharacterClick: (Int) -> Unit
        ) {
            binding.characterData = characterInfo
            binding.root.setOnSingleClickListener { onCharacterClick(characterInfo.minionId) }
        }
    }
}