package ru.gubatenko.data.prefs

import ru.gubatenko.domain.Preference

abstract class PreferenceAbstract : Preference {
    companion object {
        private const val USER_REJECTED_AUTHORIZATION_OFFER = "USER_REJECTED_AUTHORIZATION_OFFER"
        private const val DARK_THEME_ENABLED = "DARK_THEME_ENABLED"
    }

    protected abstract fun getBoolean(key: String): Boolean
    protected abstract fun setBoolean(key: String, value: Boolean)

    override fun isUserRejectedAuthorizationOffer(): Boolean = getBoolean(
        USER_REJECTED_AUTHORIZATION_OFFER
    )

    override fun isUserRejectedAuthorizationOffer(value: Boolean) = setBoolean(
        USER_REJECTED_AUTHORIZATION_OFFER,
        value
    )

    override fun isDarkThemeEnabled(): Boolean = getBoolean(
        DARK_THEME_ENABLED
    )

    override fun isDarkThemeEnabled(value: Boolean) = setBoolean(
        DARK_THEME_ENABLED,
        value
    )
}
