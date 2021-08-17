package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Greeting

interface GreetingUseCase {
    suspend fun multipleGreeting(): List<Greeting>
}