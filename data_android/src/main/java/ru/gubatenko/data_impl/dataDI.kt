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
import ru.gubatenko.data.text.DynamicText
import ru.gubatenko.data.text.StaticText
import ru.gubatenko.data_impl.mapper.ActivityDtoToDomain
import ru.gubatenko.data_impl.mapper.ActivityFromDomainToStoredRoom
import ru.gubatenko.data_impl.mapper.ActivityFromStoredToDomain
import ru.gubatenko.data_impl.mapper.DomainToActivityDto
import ru.gubatenko.data_impl.sqlite.AppDatabase
import ru.gubatenko.data_impl.text.DynamicTextFirebase
import ru.gubatenko.data_impl.text.StaticTextAssets
import ru.gubatenko.domain.*
import ru.gubatenko.domain.model.Activity

val databaseModuleDI = module {
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
val mapperActionImplModuleDI = module {
    single<Mapper<ActivityDto, Activity>>(named(MAPPER_DTO_TO_DOMAIN_ACTION)) { ActivityDtoToDomain() }
    single<Mapper<Activity, ActivityDto>>(named(MAPPER_DOMAIN_TO_DTO_ACTION)) { DomainToActivityDto() }
    single<Mapper<Activity, ActivityStored>>(named(MAPPER_DOMAIN_TO_STORED_ACTION)) { ActivityFromDomainToStoredRoom() }
    single<Mapper<ActivityStored, Activity>>(named(MAPPER_STORED_TO_DOMAIN_ACTION)) { ActivityFromStoredToDomain() }
}
val prefsImplModuleDI = module {
    single { PreferenceSharedPrefs(get()) }
    single<Preference> { get<PreferenceSharedPrefs>() }
    single<DefinedPreference> { get<PreferenceSharedPrefs>() }
}
val textImplModuleDI = module {
    single<DefinedPreference> { get<PreferenceSharedPrefs>() }
    single<StaticText> { StaticTextAssets(get()) }
    single<DynamicText> { DynamicTextFirebase(get()) }
}