package ru.gubatenko.data.prefs

import ru.gubatenko.domain.DefinedPreference
import ru.gubatenko.domain.model.Pref.Companion.DARK_THEM_ENABLED_KEY

abstract class DefinedPreferenceAbstract : DefinedPreference {
    companion object {
        private const val USER_REJECTED_AUTHORIZATION_OFFER_KEY =
            "USER_REJECTED_AUTHORIZATION_OFFER"
    }

    protected abstract fun getBoolean(key: String): Boolean
    protected abstract fun setBoolean(key: String, value: Boolean)

    override fun isDarkThemEnabled(): Boolean = getBoolean(
        DARK_THEM_ENABLED_KEY
    )

    override fun isUserRejectedAuthorizationOffer(): Boolean = getBoolean(
        USER_REJECTED_AUTHORIZATION_OFFER_KEY
    )

    override fun isUserRejectedAuthorizationOffer(value: Boolean) = setBoolean(
        USER_REJECTED_AUTHORIZATION_OFFER_KEY,
        value
    )

}
