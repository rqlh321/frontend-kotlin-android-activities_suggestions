package ru.gubatenko.domain

interface DefinedPreference {

    fun isDarkThemEnabled(): Boolean
    fun isUserRejectedAuthorizationOffer(): Boolean
    fun isUserRejectedAuthorizationOffer(value: Boolean)

}
