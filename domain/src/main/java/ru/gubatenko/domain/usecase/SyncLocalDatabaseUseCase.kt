package ru.gubatenko.domain.usecase

interface SyncLocalDatabaseUseCase {
    suspend fun execute()
}