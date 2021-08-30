package ru.gubatenko.domain_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.domain_impl.repo.ActivityRepoImpl
import ru.gubatenko.domain_impl.use_case.ActivityUseCaseImpl

val repoImplModuleDI = module {
    single<ActivityRepo> {
        ActivityRepoImpl(
            dao = get(),
            activitySourceService = get(),
            userService = get(),
            domainToStored = get(named("domainToStored")),
            storedToDomain = get(named("storedToDomain")),
            domainToDto = get(named("domainToDto")),
            dtoToDomain = get(named("dtoToDomain")),
        )
    }
}
val usaCaseImplModuleDI = module {
    single<ActivityUseCase> { ActivityUseCaseImpl(repo = get()) }
}