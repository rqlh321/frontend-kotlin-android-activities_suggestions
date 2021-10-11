package ru.gubatenko.data.prefs

import ru.gubatenko.domain.DefinedPreference

abstract class DefinedPreferenceAbstract : DefinedPreference {
    companion object {
        private const val USER_REJECTED_AUTHORIZATION_OFFER = "USER_REJECTED_AUTHORIZATION_OFFER"
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

}
