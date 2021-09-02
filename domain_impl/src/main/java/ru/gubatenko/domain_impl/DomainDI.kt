package ru.gubatenko.domain_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.repo.ActivityRepo
import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.*
import ru.gubatenko.domain_impl.repo.ActivityRepoImpl
import ru.gubatenko.domain_impl.repo.UserRepoImpl
import ru.gubatenko.domain_impl.use_case.*

val rootScopeRepoImplModuleDI = module {
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
    single<UserRepo> { UserRepoImpl(service = get()) }
}
val rootScopeUsaCaseImplModuleDI = module {
    single<GetSuggestedActivityUseCase> { GetSuggestedActivityUseCaseImpl(repo = get()) }
    single<SaveActivityToLocalStorageUseCase> { SaveActivityToLocalStorageUseCaseImpl(repo = get(), prefs = get()) }
    single<SyncActivitiesWithServerUseCase> { SyncActivitiesWithServerUseCaseImpl(repo = get()) }
    single<AuthOfferIsViewedUseCase> { AuthOfferIsViewedUseCaseImpl(prefs = get()) }
    single<IsAuthorizedUseCase> { IsAuthorizedUseCaseImpl(repo = get()) }
}