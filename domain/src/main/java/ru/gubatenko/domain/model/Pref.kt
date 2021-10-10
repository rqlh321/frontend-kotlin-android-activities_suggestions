package ru.gubatenko.domain.model

sealed class Pref {
    companion object {
        const val PREF_THEME_ID = "PREF_THEME"

        const val SWITCH_PREF_VIEW = 0
    }

    abstract fun type(): Int
}

data class SwitchPref(
    val id: String,
    val title: String,
    val isOn: Boolean,
) : Pref() {
    override fun type() = SWITCH_PREF_VIEW
}
