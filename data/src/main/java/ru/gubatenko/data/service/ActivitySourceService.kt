package ru.gubatenko.data.service

import ru.gubatenko.data.dto.ActivityDto

interface ActivitySourceService {

    suspend fun activity(): ActivityDto
}