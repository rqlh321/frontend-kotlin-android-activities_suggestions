package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Activity

interface ActivityUseCase {
    suspend fun activity(): Activity
    suspend fun save(activity: Activity)
}