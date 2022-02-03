package ru.gubatenko.domain_impl

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.*
import ru.gubatenko.domain.pref.AuthPreference
import ru.gubatenko.domain.pref.ThemPreference
import ru.gubatenko.domain.repo.IdeaRepo
import ru.gubatenko.domain.repo.UserRepo
import ru.gubatenko.domain.usecase.*
import ru.gubatenko.domain_impl.prefs.AuthPreferenceImpl
import ru.gubatenko.domain_impl.prefs.ThemPreferenceImpl
import ru.gubatenko.domain_impl.repo.IdeaRepoImpl
import ru.gubatenko.domain_impl.repo.UserRepoImpl
import ru.gubatenko.domain_impl.use_case.*

fun repoImplModuleDI() = module {
    single<AuthPreference> { AuthPreferenceImpl(get()) }
    single<ThemPreference> { ThemPreferenceImpl(get()) }

    single<IdeaRepo> {
        IdeaRepoImpl(
            ideaDao = get(named(DAO_IDEA_SQLITE)),
            ideaService = get(),
            userService = get(),
            ideaDomainToStoredMapper = get(named(MAPPER_DOMAIN_TO_STORED_SQLITE_ACTION)),
            ideaStoredToDomainMapper = get(named(MAPPER_STORED_TO_DOMAIN_ACTION)),
            ideaDomainToDtoMapper = get(named(MAPPER_DOMAIN_TO_DTO_ACTION)),
            ideaDtoToDomainMapper = get(named(MAPPER_DTO_TO_DOMAIN_ACTION)),
        )
    }
    single<UserRepo> { UserRepoImpl(userService = get()) }
}
fun usaCaseImplModuleDI() = module {
    single<GetSuggestedActivityUseCase> { GetSuggestedActivityUseCaseImpl(repo = get()) }
    single<SaveActivityToLocalStorageUseCase> { SaveActivityToLocalStorageUseCaseImpl(repo = get(), prefs = get()) }
    single<SyncActivitiesWithServerUseCase> { SyncActivitiesWithServerUseCaseImpl(repo = get()) }
    single<AuthOfferIsViewedUseCase> { AuthOfferIsViewedUseCaseImpl(prefs = get()) }
    single<IsAuthorizedUseCase> { IsAuthorizedUseCaseImpl(repo = get()) }
    single<GetSignedInUserUseCase> { GetSignedInUserUseCaseImpl(repo = get()) }
    single<SignOutUseCase> { SignOutUseCaseImpl(userRepo = get(), ideaRepo = get()) }
    single<GetAllPromiseUseCase> { GetAllSavedActivitiesUseCaseImpl(repo = get()) }
    single<GetStaticTextUseCase> { GetStaticTextUseCaseImpl(staticText = get()) }
    single<GetDynamicTextUseCase> { GetDynamicTextUseCaseImpl(dynamicText = get()) }
    single<SyncLocalDatabaseUseCase> {  SyncLocalDatabaseUseCaseImpl(repo = get())}
    single<SignInUseCase> { SignInUseCaseImpl( repo = get()) }
    single<GetProfilePrefsUseCase> { GetProfilePrefsUseCaseImpl(prefs = get(), getStaticTextUseCase = get(), userRepo = get()) }
    single<SetPrefUseCase> { SetPrefUseCaseImpl(prefs = get()) }
}
