package ru.gubatenko.data.service

import ru.gubatenko.data.dto.IdeaDto

interface IdeaSourceService {

    suspend fun idea(): IdeaDto
}