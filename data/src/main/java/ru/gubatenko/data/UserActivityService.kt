package ru.gubatenko.data

import ru.gubatenko.data.dto.ActivityDto

interface UserActivityService {

    suspend fun activity(): ActivityDto
}