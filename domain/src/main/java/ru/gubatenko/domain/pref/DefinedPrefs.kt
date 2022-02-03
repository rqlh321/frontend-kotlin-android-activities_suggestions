package ru.gubatenko.domain.pref

interface DefinedPrefs {
    fun String.code() = this::class.java.name + "." + this
}
