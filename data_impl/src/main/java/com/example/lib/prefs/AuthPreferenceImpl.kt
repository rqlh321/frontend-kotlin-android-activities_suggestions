package com.example.lib.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first
import ru.gubatenko.data.pref.AuthPreference
import ru.gubatenko.data.pref.AuthPreference.Companion.USER_REJECTED_AUTHORIZATION_OFFER_KEY

class AuthPreferenceImpl(
    private val store: DataStore<Preferences>,
) : AuthPreference {

    override suspend fun isUserRejectedAuthorizationOffer(): Boolean = store.data
        .first()[booleanPreferencesKey(USER_REJECTED_AUTHORIZATION_OFFER_KEY)]
        ?: false

    override suspend fun isUserRejectedAuthorizationOffer(value: Boolean) {
        store.edit { it[booleanPreferencesKey(USER_REJECTED_AUTHORIZATION_OFFER_KEY)] = value }
    }

}
