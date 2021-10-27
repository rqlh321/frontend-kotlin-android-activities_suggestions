package com.example.feature_accepted_activities_android

import org.koin.dsl.module
import org.mockito.Mockito
import ru.gubatenko.domain.usecase.*

val mockedUseCase = module {
    single<GetStaticTextUseCase> { Mockito.mock(GetStaticTextUseCase::class.java) }
    single<SignOutUseCase> { Mockito.mock(SignOutUseCase::class.java) }
    single<GetSignedInUserUseCase> { Mockito.mock(GetSignedInUserUseCase::class.java) }
    single<SetPrefUseCase> { Mockito.mock(SetPrefUseCase::class.java) }
    single<GetProfilePrefsUseCase> { Mockito.mock(GetProfilePrefsUseCase::class.java) }
    single<LongTermWorkUseCase> { Mockito.mock(LongTermWorkUseCase::class.java) }
}