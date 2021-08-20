package ru.gubatenko.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactory {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.boredapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun activity() = retrofit.create(ActivityService::class.java)
}