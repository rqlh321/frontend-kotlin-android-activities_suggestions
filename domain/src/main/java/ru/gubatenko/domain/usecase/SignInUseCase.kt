package ru.gubatenko.domain.usecase

interface SignInUseCase {
    suspend fun execute(credential: Any)
}