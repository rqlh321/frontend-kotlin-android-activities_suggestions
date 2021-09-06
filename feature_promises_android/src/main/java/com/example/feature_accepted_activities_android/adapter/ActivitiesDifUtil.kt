package com.example.feature_accepted_activities_android.adapter

import android.app.Activity
import androidx.recyclerview.widget.DiffUtil

class ActivitiesDifUtil(
    private val old: List<ActivityModel>,
    private val new: List<ActivityModel>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = old.size

    override fun getNewListSize() = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition] == new[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = old[oldItemPosition] == new[newItemPosition]
}