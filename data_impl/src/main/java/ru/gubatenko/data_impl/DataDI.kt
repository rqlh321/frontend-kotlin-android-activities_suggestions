package ru.gubatenko.data_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.data.service.ActivitySourceService
import ru.gubatenko.data_impl.mapper.ActivityDomainToStoredMapper
import ru.gubatenko.data_impl.mapper.ActivityDtoToDomainMapper
import ru.gubatenko.data_impl.mapper.ActivityStoredToDomainMapper
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.Mapper

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

val mapperImplModuleDI = module {
    single<Mapper<ActivityDto, Activity>>(named("1")) { ActivityDtoToDomainMapper() }
    single<Mapper<Activity, ActivityStored>>(named("2")) { ActivityDomainToStoredMapper() }
    single<Mapper<ActivityStored, Activity>>(named("3")) { ActivityStoredToDomainMapper() }
}