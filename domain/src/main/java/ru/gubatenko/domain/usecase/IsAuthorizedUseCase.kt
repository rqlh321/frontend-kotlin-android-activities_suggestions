package ru.gubatenko.domain.usecase

interface IsAuthorizedUseCase {
    suspend fun execute(): Boolean
}