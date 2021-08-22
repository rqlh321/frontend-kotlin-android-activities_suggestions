package ru.gubatenko.patterns

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import ru.gubatenko.data.ActivitySourceService
import ru.gubatenko.data.dto.ActivityDto

object ServiceFactory {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun activitySourceService() = retrofit.create(
        ActivitySourceServiceRetrofit::class.java
    )

    // fun userActivityService(): UserActivityService = Firebase.firestore

}

interface ActivitySourceServiceRetrofit : ActivitySourceService {
    @GET("activity")
    override suspend fun activity(): ActivityDto
}