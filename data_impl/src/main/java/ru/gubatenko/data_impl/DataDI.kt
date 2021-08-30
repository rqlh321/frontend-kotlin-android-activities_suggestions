package ru.gubatenko.data_impl

import androidx.room.Room
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.data.Mapper
import ru.gubatenko.data.dao.ActivityDao
import ru.gubatenko.data.dto.ActivityDto
import ru.gubatenko.data.entity.ActivityStored
import ru.gubatenko.data.service.ActivitySourceService
import ru.gubatenko.data.service.UserService
import ru.gubatenko.data_impl.mapper.ActivityDtoToDomain
import ru.gubatenko.data_impl.mapper.ActivityFromDomainToStoredRoom
import ru.gubatenko.data_impl.mapper.ActivityFromStoredToDomain
import ru.gubatenko.data_impl.mapper.DomainToActivityDto
import ru.gubatenko.data_impl.service.UserServiceImpl
import ru.gubatenko.data_impl.sqlite.AppDatabase
import ru.gubatenko.domain.model.Activity

val daoModuleDI = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "database-name").build() }
    single<ActivityDao<*>> { get<AppDatabase>().activityDao() }
}
val serviceImplModuleDI = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ActivitySourceService> { get<Retrofit>().create(ActivitySourceServiceRetrofit::class.java) }
    single<UserService> { UserServiceImpl() }
}

val dtoMapperImplModuleDI = module {
    single<Mapper<ActivityDto, Activity>>(named("dtoToDomain")) { ActivityDtoToDomain() }
    single<Mapper<Activity, ActivityDto>>(named("domainToDto")) { DomainToActivityDto() }
}
val storedMapperImplModuleDI = module {
    single<Mapper<Activity, ActivityStored>>(named("domainToStored")) { ActivityFromDomainToStoredRoom() }
    single<Mapper<ActivityStored, Activity>>(named("storedToDomain")) { ActivityFromStoredToDomain() }
}
