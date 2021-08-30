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
            service = get(),
            dtoToDomain = get(named("1")),
            domainToStored = get(named("2")),
            storedToDomain = get(named("3"))
        )
    }
}
val usaCaseImplModuleDI = module {
    single<ActivityUseCase> { ActivityUseCaseImpl(repo = get()) }
}