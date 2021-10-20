package com.example.domain_android

import com.example.domain_android.usecase.LongTermWorkUseCaseImpl
import org.koin.dsl.module
import ru.gubatenko.domain.usecase.LongTermWorkUseCase

val useCaseAndroidModuleDI = module {
    single<LongTermWorkUseCase> { LongTermWorkUseCaseImpl(get()) }
}