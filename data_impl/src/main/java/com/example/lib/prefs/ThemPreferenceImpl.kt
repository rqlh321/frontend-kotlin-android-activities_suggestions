package com.example.lib.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import ru.gubatenko.data.pref.ThemPreference
import ru.gubatenko.data.pref.ThemPreference.Companion.DARK_THEM_ENABLED_KEY

class ThemPreferenceImpl(
    private val store: DataStore<Preferences>,
) : ThemPreference {

    override suspend fun isDarkThemEnabled(): Boolean = store.data
        .first()[booleanPreferencesKey(DARK_THEM_ENABLED_KEY)]
        ?: false

    override suspend fun isDarkThemEnabled(value: Boolean) {
        store.edit { it[booleanPreferencesKey(DARK_THEM_ENABLED_KEY)] = value }
    }

}
