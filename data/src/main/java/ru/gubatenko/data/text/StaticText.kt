package ru.gubatenko.data.text

interface StaticText {
    suspend fun value(key: String): String
}