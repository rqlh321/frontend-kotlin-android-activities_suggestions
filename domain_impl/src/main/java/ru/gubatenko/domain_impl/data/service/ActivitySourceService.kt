package ru.gubatenko.domain_impl.data.service

import retrofit2.http.GET
import ru.gubatenko.domain_impl.data.dto.ActivityDto

interface ActivitySourceService {

    @GET("activity")
    suspend fun activity(): ActivityDto
}