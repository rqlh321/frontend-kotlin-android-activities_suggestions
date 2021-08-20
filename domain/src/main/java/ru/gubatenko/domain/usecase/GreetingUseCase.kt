package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Activity

interface GreetingUseCase {
    suspend fun activity(): Activity
}