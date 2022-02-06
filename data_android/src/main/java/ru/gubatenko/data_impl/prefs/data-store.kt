package ru.gubatenko.data_impl.prefs

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import ru.gubatenko.domain.SIMPLE_VALUE_DATA_STORE

val Context.customDataStore by dataStore("customData.pb", CustomDataSerializer)

val Context.dataStore by preferencesDataStore(SIMPLE_VALUE_DATA_STORE)

