package ru.gubatenko.domain.usecase

import ru.gubatenko.domain.model.Pref
import ru.gubatenko.domain.model.User

interface GetProfilePrefsUseCase {
    suspend fun execute(): List<Pref>
}
