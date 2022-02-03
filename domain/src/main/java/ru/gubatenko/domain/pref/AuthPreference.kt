package ru.gubatenko.domain.pref

interface AuthPreference : DefinedPrefs {

    companion object {
        const val USER_REJECTED_AUTHORIZATION_OFFER_KEY = "USER_REJECTED_AUTHORIZATION_OFFER"
    }

    suspend fun isUserRejectedAuthorizationOffer(): Boolean
    suspend fun isUserRejectedAuthorizationOffer(value: Boolean)

}
