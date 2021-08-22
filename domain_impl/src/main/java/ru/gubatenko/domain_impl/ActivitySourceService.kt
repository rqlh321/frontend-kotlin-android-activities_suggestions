package ru.gubatenko.domain_impl

import retrofit2.http.GET
import ru.gubatenko.domain_impl.dto.ActivityDto

interface ActivitySourceService {

    @GET("activity")
    suspend fun activity(): ActivityDto
}