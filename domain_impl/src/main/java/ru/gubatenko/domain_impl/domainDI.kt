package ru.gubatenko.domain_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.MAPPER_DOMAIN_TO_DTO_ACTION
import ru.gubatenko.domain.MAPPER_DOMAIN_TO_STORED_ACTION
import ru.gubatenko.domain.MAPPER_DTO_TO_DOMAIN_ACTION
import ru.gubatenko.domain.MAPPER_STORED_TO_DOMAIN_ACTION
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
            domainToStored = get(named(MAPPER_DOMAIN_TO_STORED_ACTION)),
            storedToDomain = get(named(MAPPER_STORED_TO_DOMAIN_ACTION)),
            domainToDto = get(named(MAPPER_DOMAIN_TO_DTO_ACTION)),
            dtoToDomain = get(named(MAPPER_DTO_TO_DOMAIN_ACTION)),
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
    single<GetSignedInUserUseCase> { GetSignedInUserUseCaseImpl(repo = get()) }
    single<SignOutUseCase> { SignOutUseCaseImpl(userRepo = get(), activityRepo = get()) }
    single<GetAllPromiseUseCase> { GetAllSavedActivitiesUseCaseImpl(repo = get()) }
    single<GetStaticTextUseCase> { GetStaticTextUseCaseImpl(staticText = get()) }
    single<GetDynamicTextUseCase> { GetDynamicTextUseCaseImpl(dynamicText = get()) }
    single<SyncLocalDatabaseUseCase> {  SyncLocalDatabaseUseCaseImpl(repo = get())}
    single<SignInUseCase> { SignInUseCaseImpl( repo = get()) }
    single<GetProfilePrefsUseCase> { GetProfilePrefsUseCaseImpl(prefs = get(), getStaticTextUseCase = get(), userRepo = get()) }
    single<SetPrefUseCase> { SetPrefUseCaseImpl(prefs = get()) }
}
