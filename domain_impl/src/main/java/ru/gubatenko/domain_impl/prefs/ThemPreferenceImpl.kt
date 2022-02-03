package ru.gubatenko.domain_impl.prefs

import ru.gubatenko.domain.pref.Preference
import ru.gubatenko.domain.pref.ThemPreference
import ru.gubatenko.domain.pref.ThemPreference.Companion.DARK_THEM_ENABLED_KEY

class ThemPreferenceImpl(private val prefs: Preference) : ThemPreference {

    override suspend fun isDarkThemEnabled(): Boolean = prefs.getBoolean(DARK_THEM_ENABLED_KEY.code())

}
