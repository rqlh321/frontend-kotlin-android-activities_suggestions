package ru.gubatenko.mvi

interface EventDispatcher<Event : Any> {
    suspend fun dispatch(event: Event)
}

class StubEventDispatcher<T : Any> : EventDispatcher<T> { override suspend fun dispatch(event: T) = Unit }
