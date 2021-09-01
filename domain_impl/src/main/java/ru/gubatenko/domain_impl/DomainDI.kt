package ru.gubatenko.domain_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.usecase.AuthOfferIsViewedUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase
import ru.gubatenko.domain.usecase.SyncActivitiesWithServerUseCase
import ru.gubatenko.domain_impl.repo.ActivityRepoImpl
import ru.gubatenko.domain_impl.use_case.AuthOfferIsViewedUseCaseImpl
import ru.gubatenko.domain_impl.use_case.GetSuggestedActivityUseCaseImpl
import ru.gubatenko.domain_impl.use_case.SaveActivityToLocalStorageUseCaseImpl
import ru.gubatenko.domain_impl.use_case.SyncActivitiesWithServerUseCaseImpl

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
    single<GetSuggestedActivityUseCase> { GetSuggestedActivityUseCaseImpl(repo = get()) }
    single<SaveActivityToLocalStorageUseCase> {
        SaveActivityToLocalStorageUseCaseImpl(
            repo = get(),
            prefs = get()
        )
    }
    single<SyncActivitiesWithServerUseCase> { SyncActivitiesWithServerUseCaseImpl(repo = get()) }
    single<AuthOfferIsViewedUseCase> { AuthOfferIsViewedUseCaseImpl(prefs = get()) }
}