package ru.gubatenko.data.service

import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.data.dto.UserDto

interface UserService {

    suspend fun user(): UserDto?

    suspend fun post(data: List<IdeaDto>)

    suspend fun get(): List<IdeaDto>

    suspend fun signIn(cred: Any)

    suspend fun signOut()
}