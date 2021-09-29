package com.example.feature_accepted_activities_android.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_accepted_activities_android.R
import ru.gubatenko.domain.model.Activity

class PromiseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val textView: TextView by lazy { view.findViewById(R.id.text_id) }

    fun bind(model: Activity) {
        textView.text = model.activity
    }
}