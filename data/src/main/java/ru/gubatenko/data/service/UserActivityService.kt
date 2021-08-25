package ru.gubatenko.data.service

import ru.gubatenko.data.dto.ActivityDto

interface UserActivityService {

    suspend fun activity(): ActivityDto
}