package ru.gubatenko.data

import ru.gubatenko.data.dto.ActivityDto

interface ActivitySourceService {

    suspend fun activity(): ActivityDto
}