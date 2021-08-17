package ru.gubatenko.core

interface Store<Action : Any> {
    suspend fun process(action: Action)
}
