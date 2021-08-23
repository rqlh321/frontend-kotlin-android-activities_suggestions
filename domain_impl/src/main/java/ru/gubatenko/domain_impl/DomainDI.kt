package ru.gubatenko.domain_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.domain_impl.data.dto.ActivityDto
import ru.gubatenko.domain_impl.data.entity.ActivityStored
import ru.gubatenko.domain_impl.data.service.ActivitySourceService
import ru.gubatenko.domain_impl.mapper.ActivityDomainToStoredMapper
import ru.gubatenko.domain_impl.mapper.ActivityDtoToDomainMapper
import ru.gubatenko.domain_impl.mapper.ActivityStoredToDomainMapper
import ru.gubatenko.domain_impl.repo.ActivitySourceRepoImpl
import ru.gubatenko.domain_impl.repo.UserActivityRepoImpl
import ru.gubatenko.domain_impl.use_case.ActivityUseCaseImpl

val serviceImplModuleDI = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single<ActivitySourceService> { get<Retrofit>().create(ActivitySourceService::class.java) }
}

val mapperImplModuleDI = module {
    single<Mapper<ActivityDto, Activity>>(named("1")) { ActivityDtoToDomainMapper() }
    single<Mapper<Activity, ActivityStored>>(named("2")) { ActivityDomainToStoredMapper() }
    single<Mapper<ActivityStored, Activity>>(named("3")) { ActivityStoredToDomainMapper() }
}
val repoImplModuleDI = module {
    single<UserActivityRepo> {
        UserActivityRepoImpl(
            dao = get(),
            fromDomain = get(named("2")),
            toDomain = get(named("3")),
        )
    }
    single<ActivitySourceRepo> {
        ActivitySourceRepoImpl(
            service = get(),
            mapper = get(named("1")),
        )
    }
}
val usaCaseImplModuleDI = module {
    single<ActivityUseCase> {
        ActivityUseCaseImpl(
            activityRepo = get(),
            userActivityRepo = get(),
        )
    }
}