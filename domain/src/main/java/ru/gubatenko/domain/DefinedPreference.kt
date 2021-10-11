package ru.gubatenko.domain

interface DefinedPreference {

    fun isUserRejectedAuthorizationOffer(): Boolean
    fun isUserRejectedAuthorizationOffer(value: Boolean)

}
