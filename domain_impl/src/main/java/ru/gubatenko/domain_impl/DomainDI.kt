package ru.gubatenko.domain_impl

import org.koin.dsl.module
import ru.gubatenko.domain.repo.ActivitySourceRepo
import ru.gubatenko.domain.repo.UserActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.domain_impl.repo.ActivitySourceRepoImpl
import ru.gubatenko.domain_impl.repo.UserActivityRepoImpl
import ru.gubatenko.domain_impl.use_case.ActivityUseCaseImpl

val repoImplModuleDI = module {
    single<UserActivityRepo> { UserActivityRepoImpl(dao = get()) }
    single<ActivitySourceRepo> { ActivitySourceRepoImpl(service = get()) }
}
val usaCaseImplModuleDI = module {
    single<ActivityUseCase> {
        ActivityUseCaseImpl(
            activityRepo = get(),
            userActivityRepo = get(),
        )
    }
}