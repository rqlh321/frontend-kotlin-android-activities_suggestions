package ru.gubatenko.domain.usecase

interface GetDynamicTextUseCase {
    suspend fun execute(key: String): String
}