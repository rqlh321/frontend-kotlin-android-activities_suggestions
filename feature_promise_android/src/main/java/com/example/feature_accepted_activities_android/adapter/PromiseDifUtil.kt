package com.example.feature_accepted_activities_android.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.gubatenko.domain.model.Idea

class PromiseDifUtil(
    private val old: List<Idea>,
    private val new: List<Idea>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition] == new[newItemPosition]
}