package ru.gubatenko.domain

interface Preference {

    fun isUserRejectedAuthorizationOffer(): Boolean
    fun isUserRejectedAuthorizationOffer(value: Boolean)

    fun isDarkThemeEnabled(): Boolean
    fun isDarkThemeEnabled(value: Boolean)
}
