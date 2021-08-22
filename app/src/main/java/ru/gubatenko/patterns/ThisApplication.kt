package ru.gubatenko.patterns

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.gubatenko.repo_impl.ActivityDataToDomain
import ru.gubatenko.repo_impl.ActivitySourceService
import ru.gubatenko.repo_impl.dto.ActivityDto
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.Mapper
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.feature_main_android.mainFeatureDi
import ru.gubatenko.repo_impl.ActivitySourceRepoImpl
import ru.gubatenko.repo_impl.UserActivityRepoImpl
import ru.gubatenko.use_case_impl.ActivityUseCaseImpl

class ThisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val useCase = module {
            single<ActivitySourceService> { ServiceFactory.activitySourceService() }
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