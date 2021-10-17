package com.example.feature_profile_android.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import ru.gubatenko.domain.model.Pref
import ru.gubatenko.domain.model.SwitchPref

class PrefViewHolder(
    view: View,
    private val switchAction: ((String, Boolean) -> Unit)? = null
) : RecyclerView.ViewHolder(view) {

    fun bind(model: Pref) = when (model) {
        is SwitchPref -> (itemView as SwitchPrefView).bind(model, switchAction)
    }
}
