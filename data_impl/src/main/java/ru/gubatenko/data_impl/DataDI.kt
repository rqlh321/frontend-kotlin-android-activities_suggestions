package ru.gubatenko.data_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.data.service.ActivitySourceService
import ru.gubatenko.data_impl.mapper.ActivityDtoToDomain
import ru.gubatenko.data_impl.mapper.ActivityFromDomainToStoredRoom
import ru.gubatenko.data_impl.mapper.ActivityFromStoredToDomain
import ru.gubatenko.domain.model.Activity

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

val dtoMapperImplModuleDI = module {
    single<Mapper<ActivityDto, Activity>>(named("1")) { ActivityDtoToDomain() }
}
val storedMapperImplModuleDI = module {
    single<Mapper<Activity, ActivityStored>>(named("2")) { ActivityFromDomainToStoredRoom() }
    single<Mapper<ActivityStored, Activity>>(named("3")) { ActivityFromStoredToDomain() }
}
