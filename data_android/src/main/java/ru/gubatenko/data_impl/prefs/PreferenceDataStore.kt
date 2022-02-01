package ru.gubatenko.data_impl.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import ru.gubatenko.data.prefs.DefinedPreferenceAbstract
import ru.gubatenko.domain.Preference

class PreferenceDataStore(private val context: Context, ) : DefinedPreferenceAbstract(), Preference {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(this::class.java.name)

    override fun getBoolean(key: String): Boolean = runBlocking {
        context.dataStore
            .data
            .first()[booleanPreferencesKey(key)]
            ?: false
    }

    override fun setBoolean(key: String, value: Boolean) {
        runBlocking {
            context.dataStore.edit { it[booleanPreferencesKey(key)] = value }
        }

    }
}
