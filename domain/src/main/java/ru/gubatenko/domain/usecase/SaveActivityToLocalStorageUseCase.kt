package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Activity

interface SaveActivityToLocalStorageUseCase {
    suspend fun execute(activity: Activity)
}