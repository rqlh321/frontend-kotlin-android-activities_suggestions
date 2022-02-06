package ru.gubatenko.data.pref

interface ThemPreference {

    companion object {
        const val DARK_THEM_ENABLED_KEY = "PREF_THEME"
    }

    suspend fun isDarkThemEnabled(): Boolean
    suspend fun isDarkThemEnabled(value: Boolean)

}
