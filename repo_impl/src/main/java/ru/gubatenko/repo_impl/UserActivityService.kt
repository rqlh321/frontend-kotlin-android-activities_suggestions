package ru.gubatenko.repo_impl

import ru.gubatenko.repo_impl.dto.ActivityDto

interface UserActivityService {

    suspend fun activity(): ActivityDto
}