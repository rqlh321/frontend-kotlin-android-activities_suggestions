package ru.gubatenko.data

import retrofit2.http.GET
import ru.gubatenko.data.dto.ActivityDto

interface ActivityService {

    @GET("activity")
    suspend fun activity(): ActivityDto
}