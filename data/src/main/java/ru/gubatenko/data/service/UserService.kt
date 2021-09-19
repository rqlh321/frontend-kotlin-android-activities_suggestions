package ru.gubatenko.data.service

import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.dto.UserDto

interface UserService {

    suspend fun user(): UserDto?

    suspend fun post(data: List<ActivityDto>)

    suspend fun get(): List<ActivityDto>

    suspend fun signOut()
}