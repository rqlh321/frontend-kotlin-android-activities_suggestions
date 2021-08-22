package ru.gubatenko.domain_impl

import ru.gubatenko.domain_impl.dto.ActivityDto

interface UserActivityService {

    suspend fun activity(): ActivityDto
}