package ru.gubatenko.data.prefs

interface Preference {
    fun isUserRejectedAuthorizationOffer(): Boolean
    fun isUserRejectedAuthorizationOffer(value: Boolean)
}