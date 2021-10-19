package com.example.lib

import retrofit2.http.GET
import ru.gubatenko.data.dto.IdeaDto
import ru.gubatenko.data.service.IdeaSourceService

interface IdeaSourceServiceRetrofit : IdeaSourceService {

    @GET("activity")
    override suspend fun idea(): IdeaDto
}