package com.example.feature_accepted_activities_android

import com.example.feature_profile.ProfileStore
import com.example.feature_profile.profileFeatureModuleDI
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.inject
import org.mockito.Mockito
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetProfilePrefsUseCase
import ru.gubatenko.domain.usecase.GetSignedInUserUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class LoadProfileUnitTest : KoinComponent {

    private val getStaticTextUseCase: GetStaticTextUseCase by inject(GetStaticTextUseCase::class.java)
    private val getSignedInUserUseCase: GetSignedInUserUseCase by inject(GetSignedInUserUseCase::class.java)
    private val getProfilePrefsUseCase: GetProfilePrefsUseCase by inject(GetProfilePrefsUseCase::class.java)

    private val store: ProfileStore by inject(ProfileStore::class.java)

    @Before
    fun setup(): Unit = runBlocking {
        startKoin {
            modules(
                mockedUseCase,
                stubStateEvent,
                profileFeatureModuleDI
            )
        }
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Profile.DEFAULT_NAME)).thenReturn(DEFAULT_NAME)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Profile.SIGN_IN)).thenReturn(SIGN_IN)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Profile.SIGN_OUT)).thenReturn(SIGN_OUT)
    }

    @After
    fun shutdown() {
        stopKoin()
    }

    @Test
    fun success() = runBlocking {
        Mockito.`when`(getSignedInUserUseCase.execute()).thenReturn(user)
        Mockito.`when`(getProfilePrefsUseCase.execute()).thenReturn(prefs)

        store.process(ProfileStore.Action.LoadProfile)

        TestCase.assertEquals(USER_NAME, store.stateObservable.stateValue.name)
        TestCase.assertEquals(USER_AVATAR, store.stateObservable.stateValue.avatar)
        TestCase.assertEquals(USER_EMAIL, store.stateObservable.stateValue.email)
        TestCase.assertEquals(prefs, store.stateObservable.stateValue.prefs)
        TestCase.assertEquals(SIGN_IN, store.stateObservable.stateValue.signInButtonText)
        TestCase.assertEquals(false, store.stateObservable.stateValue.isSignInButtonVisible)
        TestCase.assertEquals(SIGN_OUT, store.stateObservable.stateValue.signOutButtonText)
        TestCase.assertEquals(true, store.stateObservable.stateValue.isSignOutButtonVisible)
    }

}