package ru.gubatenko.domain.pref

interface Preference {
    suspend fun getBoolean(key: String): Boolean
    suspend fun setBoolean(key: String, value: Boolean)
}
