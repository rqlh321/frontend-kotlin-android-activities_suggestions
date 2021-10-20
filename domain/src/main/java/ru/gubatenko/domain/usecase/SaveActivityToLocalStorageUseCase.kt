package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Idea

interface SaveActivityToLocalStorageUseCase {
    suspend fun execute(idea: Idea)
}