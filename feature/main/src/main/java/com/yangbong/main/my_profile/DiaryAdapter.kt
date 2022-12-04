package com.yangbong.main.my_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yangbong.core_ui.extension.getDimen
import com.yangbong.core_ui.extension.setOnSingleClickListener
import com.yangbong.core_ui.util.ItemDiffCallback
import com.yangbong.damedame.main.databinding.ItemDiaryBinding
import com.yangbong.damedame.shared.R
import com.yangbong.domain.entity.DiaryInfo

class DiaryAdapter(
    private val onLockCLick: (Boolean, Int) -> Unit,
    private val onDiaryClick: (Int) -> Unit
) : ListAdapter<DiaryInfo, DiaryAdapter.DiaryViewHolder>(
    ItemDiffCallback<DiaryInfo>(
        onContentsTheSame = { oldItem, newItem -> oldItem == newItem },
        onItemsTheSame = { oldItem, newItem -> oldItem.diaryId == newItem.diaryId }
    )
) {
    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryViewHolder {
        if (!::inflater.isInitialized)
            inflater = LayoutInflater.from(parent.context)

        val binding = ItemDiaryBinding.inflate(inflater, parent, false)
        return DiaryViewHolder(
            binding.apply {
                root.apply {
                    layoutParams = ConstraintLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    ).apply {
                        setMargins(
                            getDimen(R.dimen.diary_margin), 0, getDimen(R.dimen.diary_margin), 0
                        )
                    }
                }
            }
        )
    }

    override fun onBindViewHolder(holder: DiaryViewHolder, position: Int) {
        holder.onBind(getItem(position), onLockCLick, onDiaryClick)
    }

    class DiaryViewHolder(private val binding: ItemDiaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            diaryInfo: DiaryInfo,
            onLockCLick: (Boolean, Int) -> Unit,
            onDiaryClick: (Int) -> Unit
        ) {
            binding.diaryData = diaryInfo
            binding.btnLock.setOnSingleClickListener {
                onLockCLick(mapToBoolean(diaryInfo.visibility), diaryInfo.diaryId)
            }
            binding.root.setOnSingleClickListener { onDiaryClick(diaryInfo.diaryId) }
        }

        private fun mapToBoolean(visibility: Int): Boolean {
            return if (visibility == 0) false
            else visibility == 1
        }
    }

}
