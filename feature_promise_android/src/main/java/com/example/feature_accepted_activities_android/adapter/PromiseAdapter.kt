package com.example.feature_accepted_activities_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_accepted_activities_android.R
import ru.gubatenko.domain.model.Activity

class PromiseAdapter : RecyclerView.Adapter<PromiseViewHolder>() {

    private var data = emptyList<Activity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PromiseViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_promise,
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: PromiseViewHolder,
        position: Int
    ) = holder.bind(data[position])

    override fun getItemCount() = data.size

    fun set(newData: List<Activity>) {
        val diffCallback = PromiseDifUtil(old = data, new = newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data = newData
        diffResult.dispatchUpdatesTo(this)
    }
}