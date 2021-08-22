package ru.gubatenko.repo_impl

import ru.gubatenko.repo_impl.dto.ActivityDto

interface ActivitySourceService {

    suspend fun activity(): ActivityDto
}