package com.example.feature_profile

sealed class PrefModel {
    companion object {
        const val SWITCH_PREF_VIEW = 0
    }

    abstract fun type(): Int
}

data class SwitchPrefModel(
    val id: String,
    val title: String,
    val isOn: Boolean,
) : PrefModel() {
    override fun type() = SWITCH_PREF_VIEW
}
