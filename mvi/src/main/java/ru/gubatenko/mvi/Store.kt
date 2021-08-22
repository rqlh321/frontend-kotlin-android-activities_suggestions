package ru.gubatenko.mvi

interface Store<Action : Any> {
    suspend fun process(action: Action)
}
