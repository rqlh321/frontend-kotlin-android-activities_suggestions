package ru.gubatenko.patterns

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.domain_impl.ActivityDataToDomain
import ru.gubatenko.domain_impl.ActivitySourceRepoImpl
import ru.gubatenko.domain_impl.ActivitySourceService
import ru.gubatenko.domain_impl.ActivityUseCaseImpl
import ru.gubatenko.domain_impl.UserActivityRepoImpl
import ru.gubatenko.domain_impl.dto.ActivityDto
import ru.gubatenko.feature_main_android.mainFeatureDi

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val useCase = module {
            single<Retrofit> {
                Retrofit.Builder()
                    .baseUrl("https://www.boredapi.com/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            single<ActivitySourceService> { get<Retrofit>().create(ActivitySourceService::class.java) }

            single<Mapper<ActivityDto, Activity>> { ActivityDataToDomain() }
            single<UserActivityRepo> { UserActivityRepoImpl() }
            single<ActivitySourceRepo> {
                ActivitySourceRepoImpl(
                    service = get(),
                    mapper = get(),
                )
            }

            single<ActivityUseCase> {
                ActivityUseCaseImpl(
                    activityRepo = get(),
                    userActivityRepo = get(),
                )
            }
        }
        startKoin {
            androidLogger()
            androidContext(this@ThisApplication)
            modules(
                useCase,
                mainFeatureDi
            )
        }
    }
}