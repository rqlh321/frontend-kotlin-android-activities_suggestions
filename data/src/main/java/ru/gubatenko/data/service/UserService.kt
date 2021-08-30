package ru.gubatenko.data.service

import ru.gubatenko.data.dto.ActivityDto

interface UserService {

    suspend fun post(data: List<ActivityDto>)
}