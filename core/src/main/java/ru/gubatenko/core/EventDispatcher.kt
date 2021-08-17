package ru.gubatenko.core

interface EventDispatcher<Event : Any> {
    suspend fun dispatch(event: Event)
}
