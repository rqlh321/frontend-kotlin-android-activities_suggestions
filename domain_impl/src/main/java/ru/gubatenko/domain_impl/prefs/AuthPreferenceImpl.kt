package ru.gubatenko.domain_impl.prefs

import ru.gubatenko.domain.pref.AuthPreference
import ru.gubatenko.domain.pref.AuthPreference.Companion.USER_REJECTED_AUTHORIZATION_OFFER_KEY
import ru.gubatenko.domain.pref.Preference

class AuthPreferenceImpl(private val prefs: Preference) : AuthPreference {

    override suspend fun isUserRejectedAuthorizationOffer(): Boolean = prefs.getBoolean(
        USER_REJECTED_AUTHORIZATION_OFFER_KEY.code()
    )

    override suspend fun isUserRejectedAuthorizationOffer(value: Boolean) = prefs.setBoolean(
        USER_REJECTED_AUTHORIZATION_OFFER_KEY.code(),
        value
    )

}
