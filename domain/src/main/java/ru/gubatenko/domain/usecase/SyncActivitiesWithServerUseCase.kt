package ru.gubatenko.domain.usecase

interface SyncActivitiesWithServerUseCase {
    suspend fun execute()
}