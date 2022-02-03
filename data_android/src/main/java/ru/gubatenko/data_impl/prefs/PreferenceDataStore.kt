package ru.gubatenko.data_impl.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import ru.gubatenko.domain.pref.Preference

class PreferenceDataStore(context: Context) : Preference {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(this::class.java.name)

    private val dataStore = context.dataStore

    override suspend fun getBoolean(key: String): Boolean {
        return dataStore
            .data
            .first()[booleanPreferencesKey(key)]
            ?: false
    }

    override suspend fun setBoolean(key: String, value: Boolean) {
        dataStore.edit { it[booleanPreferencesKey(key)] = value }
    }

}
