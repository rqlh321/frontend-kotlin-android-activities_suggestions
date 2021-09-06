package ru.gubatenko.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.gubatenko.domain.model.Activity

interface GetAllSavedActivitiesUseCase {
    suspend fun execute(): Flow<List<Activity>>
}