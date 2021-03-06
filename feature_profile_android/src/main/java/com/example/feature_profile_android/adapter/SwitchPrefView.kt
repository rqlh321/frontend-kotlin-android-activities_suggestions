package com.example.feature_profile_android.adapter

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import ru.gubatenko.domain.model.SwitchPref
import com.example.feature_profile_android.R
import com.google.android.material.switchmaterial.SwitchMaterial

class SwitchPrefView(context: Context) : LinearLayout(context) {

    private val title: TextView by lazy { findViewById(R.id.title_id) }
    private val state: SwitchMaterial by lazy { findViewById(R.id.state_id) }

    init {
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            context.resources.getDimension(R.dimen.medium_item_height).toInt()
        )
        orientation = HORIZONTAL
        inflate(getContext(), R.layout.list_item_pref_switch, this)
    }

    fun bind(model: SwitchPref, action: ((String, Boolean) -> Unit)? = null) {
        title.text = model.title
        state.isChecked = model.isOn
        state.setOnCheckedChangeListener { _, isChecked -> action?.invoke(model.id, isChecked) }
    }
}
