package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Activity

interface GetSuggestedActivityUseCase {
    suspend fun execute(): Activity
}