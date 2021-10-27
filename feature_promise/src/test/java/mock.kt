package com.example.feature_accepted_activities_android

import org.koin.dsl.module
import org.mockito.Mockito
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

val mockedUseCase = module {
    single<GetAllPromiseUseCase> { Mockito.mock(GetAllPromiseUseCase::class.java) }
    single<GetStaticTextUseCase> { Mockito.mock(GetStaticTextUseCase::class.java) }
}