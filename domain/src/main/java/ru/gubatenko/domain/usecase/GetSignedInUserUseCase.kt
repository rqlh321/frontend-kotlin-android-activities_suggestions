package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.User

interface GetSignedInUserUseCase {
    suspend fun execute(): User?
}