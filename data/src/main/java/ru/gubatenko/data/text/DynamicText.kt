package ru.gubatenko.data.text

interface DynamicText {
    suspend fun value(key: String): String
}