package com.example.feature_profile_android.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_profile.PrefModel

class PrefAdapter(
    private val switchAction: ((String, Boolean) -> Unit)? = null
) : RecyclerView.Adapter<PrefViewHolder>() {

    var data: List<PrefModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PrefViewHolder(
        viewType.toView(parent.context),
        switchAction
    )

    override fun getItemViewType(position: Int) = data[position].type()

    override fun onBindViewHolder(holder: PrefViewHolder, position: Int) = holder.bind(
        data[position]
    )

    override fun getItemCount() = data.size

    private fun Int.toView(context: Context): View = when (this) {
        PrefModel.SWITCH_PREF_VIEW -> SwitchPrefView(context)
        else -> throw IllegalArgumentException()
    }
}
