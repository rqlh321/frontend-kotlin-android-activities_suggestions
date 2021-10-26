package com.example.feature_accepted_activities_android

import com.example.audit.Logger
import com.example.audit.LoggerStub
import org.koin.dsl.module
import org.mockito.Mockito
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase

val mockedUseCase = module {
    single<Logger> { LoggerStub() }
    single<GetSuggestedActivityUseCase> { Mockito.mock(GetSuggestedActivityUseCase::class.java) }
    single<GetStaticTextUseCase> { Mockito.mock(GetStaticTextUseCase::class.java) }
    single<SaveActivityToLocalStorageUseCase> { Mockito.mock(SaveActivityToLocalStorageUseCase::class.java) }
}