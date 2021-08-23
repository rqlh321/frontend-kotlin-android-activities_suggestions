package ru.gubatenko.domain_impl.data.service

import ru.gubatenko.domain_impl.data.dto.ActivityDto

interface UserActivityService {

    suspend fun activity(): ActivityDto
}