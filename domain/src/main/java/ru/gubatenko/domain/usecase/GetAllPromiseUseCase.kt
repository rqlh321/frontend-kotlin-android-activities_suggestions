package ru.gubatenko.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.gubatenko.domain.model.Idea

interface GetAllPromiseUseCase {
    suspend fun execute(): Flow<List<Idea>>
}