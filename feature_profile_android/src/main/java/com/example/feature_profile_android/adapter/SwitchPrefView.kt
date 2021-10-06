package com.example.feature_profile_android.adapter

import android.content.Context
import android.widget.FrameLayout
import android.widget.TextView
import com.example.feature_profile.SwitchPrefModel
import com.example.feature_profile_android.R
import com.google.android.material.switchmaterial.SwitchMaterial

class SwitchPrefView(context: Context) : FrameLayout(context) {

    private val title: TextView by lazy { findViewById(R.id.title_id) }
    private val state: SwitchMaterial by lazy { findViewById(R.id.state_id) }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        inflate(getContext(), R.layout.list_item_pref_switch, this)
    }

    fun bind(model: SwitchPrefModel, action: ((String, Boolean) -> Unit)? = null) {
        title.text = model.title
        state.isChecked = model.isOn
        state.setOnCheckedChangeListener { compoundButton, isChecked ->
            action?.invoke(model.id, isChecked)
        }
    }
}
