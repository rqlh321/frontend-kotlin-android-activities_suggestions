package ru.gubatenko.data.pref

interface AuthPreference {

    companion object {
        const val USER_REJECTED_AUTHORIZATION_OFFER_KEY = "USER_REJECTED_AUTHORIZATION_OFFER"
    }

    suspend fun isUserRejectedAuthorizationOffer(): Boolean
    suspend fun isUserRejectedAuthorizationOffer(value: Boolean)

}
