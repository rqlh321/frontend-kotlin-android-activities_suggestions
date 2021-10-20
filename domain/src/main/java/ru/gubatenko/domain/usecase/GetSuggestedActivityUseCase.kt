package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Idea

interface GetSuggestedActivityUseCase {
    suspend fun execute(): Idea
}