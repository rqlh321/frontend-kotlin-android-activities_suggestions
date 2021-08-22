package ru.gubatenko.mvi

interface EventDispatcher<Event : Any> {
    suspend fun dispatch(event: Event)
}
