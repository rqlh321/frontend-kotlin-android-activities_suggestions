package com.example.feature_accepted_activities_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_accepted_activities_android.R

class ActivitiesAdapter : RecyclerView.Adapter<ActivitiesViewHolder>() {

    private var data = emptyList<ActivityModel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ActivitiesViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_activity,
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ActivitiesViewHolder,
        position: Int
    ) = holder.bind(data[position])

    override fun getItemCount() = data.size

    fun set(newData: List<ActivityModel>) {
        val diffCallback = ActivitiesDifUtil(old = data, new = newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data = newData
        diffResult.dispatchUpdatesTo(this)
    }
}