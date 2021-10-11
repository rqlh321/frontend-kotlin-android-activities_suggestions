package ru.gubatenko.domain

interface Preference {
    fun getBoolean(key: String): Boolean
    fun setBoolean(key: String, value: Boolean)
}
