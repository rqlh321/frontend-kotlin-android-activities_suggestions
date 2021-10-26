package com.example.feature_accepted_activities_android

import com.example.audit.Logger
import com.example.audit.LoggerStub
import org.koin.dsl.module
import org.mockito.Mockito
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

val mockedUseCase = module {
    single<Logger> { LoggerStub() }
    single<GetAllPromiseUseCase> { Mockito.mock(GetAllPromiseUseCase::class.java) }
    single<GetStaticTextUseCase> { Mockito.mock(GetStaticTextUseCase::class.java) }
}