package ru.gubatenko.data_impl

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.service.ActivitySourceService

val daoModuleDI = module {
    single<ActivityDao> { ActionDaoSharedPrefImpl() }
}
val serviceImplModuleDI = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ActivitySourceService> { get<Retrofit>().create(ActivitySourceServiceRetrofit::class.java) }
}