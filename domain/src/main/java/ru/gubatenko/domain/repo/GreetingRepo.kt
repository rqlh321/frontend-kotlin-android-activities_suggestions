package ru.gubatenko.domain.repo

import ru.gubatenko.domain.model.Greeting

interface GreetingRepo {
    suspend fun create(value: Greeting)
    suspend fun read(): Greeting
    suspend fun update(value: Greeting)
    suspend fun delete(value: Greeting)
}