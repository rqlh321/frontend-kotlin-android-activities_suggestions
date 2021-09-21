package ru.gubatenko.patterns.work

import org.koin.dsl.module
import ru.gubatenko.domain.usecase.LongTermWorkUseCase

val workerUseCaseImplModuleDI = module {
    single<LongTermWorkUseCase> { LongTermWorkUseCaseImpl(get()) }
}