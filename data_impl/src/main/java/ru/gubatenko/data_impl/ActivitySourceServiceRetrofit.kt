package ru.gubatenko.data_impl

import retrofit2.http.GET
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.service.ActivitySourceService

interface ActivitySourceServiceRetrofit : ActivitySourceService {

    @GET("activity")
    override suspend fun activity(): ActivityDto
}