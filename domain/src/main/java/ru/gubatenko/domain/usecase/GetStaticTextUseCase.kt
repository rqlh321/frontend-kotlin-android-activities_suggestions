package ru.gubatenko.domain.usecase

interface GetStaticTextUseCase {
    suspend fun execute(key: String): String
}