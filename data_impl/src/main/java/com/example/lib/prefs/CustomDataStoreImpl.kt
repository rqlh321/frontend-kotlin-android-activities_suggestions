package com.example.lib.prefs

import androidx.datastore.core.DataStore
import ru.gubatenko.data.pref.CustomDataStore
import ru.gubatenko.data_impl.prefs.CustomData

class CustomDataStoreImpl(
    private val customDataStore: DataStore<CustomData>,
) : CustomDataStore {
}
