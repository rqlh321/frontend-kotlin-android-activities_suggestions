package ru.gubatenko.domain.pref

interface ThemPreference : DefinedPrefs {

    companion object {
        const val DARK_THEM_ENABLED_KEY = "PREF_THEME"
    }

    suspend fun isDarkThemEnabled(): Boolean

}
