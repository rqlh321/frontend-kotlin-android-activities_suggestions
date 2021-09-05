package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Activity

interface GetAllSavedActivitiesUseCase {
    suspend fun execute(): List<Activity>
}