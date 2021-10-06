package com.example.feature_profile_android.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_profile.PrefModel
import com.example.feature_profile.SwitchPrefModel

class PrefViewHolder(
    view: View,
    private val switchAction: ((String, Boolean) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    fun bind(model: PrefModel) = when (model) {
        is SwitchPrefModel -> (itemView as SwitchPrefView).bind(model, switchAction)
    }
}
