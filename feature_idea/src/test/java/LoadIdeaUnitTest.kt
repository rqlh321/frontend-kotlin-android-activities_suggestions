package com.example.feature_accepted_activities_android

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.java.KoinJavaComponent.inject
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.feature_main.ideaStoreModuleDI

class LoadIdeaUnitTest : KoinComponent {

    private val getSuggestedActivityUseCase: GetSuggestedActivityUseCase by inject(GetSuggestedActivityUseCase::class.java)
    private val getStaticTextUseCase: GetStaticTextUseCase by inject(GetStaticTextUseCase::class.java)

    private val store: IdeaStore by inject(IdeaStore::class.java)

    @Before
    fun setup(): Unit = runBlocking {
        startKoin {
            modules(
                mockedUseCase,
                stubStateEvent,
                ideaStoreModuleDI
            )
        }
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Main.SAVE)).thenReturn(SAVE)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Main.NEXT)).thenReturn(NEXT)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Common.ERROR)).thenReturn(ERROR)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Common.RETRY)).thenReturn(RETRY)
    }

    @After
    fun shutdown() {
        stopKoin()
    }

    @Test
    fun success() = runBlocking {
        Mockito.`when`(getSuggestedActivityUseCase.execute()).thenReturn(idea)

        store.process(IdeaStore.Action.LoadContent)

        assertEquals(idea, store.stateObservable.stateValue.idea)
        assertEquals(true, store.stateObservable.stateValue.isIdeaTextVisible)

        assertEquals(null, store.stateObservable.stateValue.errorText)
        assertEquals(false, store.stateObservable.stateValue.isErrorTextVisible)

        assertEquals(null, store.stateObservable.stateValue.retryButtonText)
        assertEquals(false, store.stateObservable.stateValue.isRetryButtonVisible)

        assertEquals(SAVE, store.stateObservable.stateValue.saveButtonText)
        assertEquals(true, store.stateObservable.stateValue.isSaveButtonVisible)
        assertEquals(true, store.stateObservable.stateValue.isSaveButtonClickable)

        assertEquals(NEXT, store.stateObservable.stateValue.nextButtonText)
        assertEquals(true, store.stateObservable.stateValue.isNextButtonVisible)
        assertEquals(true, store.stateObservable.stateValue.isNextButtonClickable)

        assertEquals(false, store.stateObservable.stateValue.isLoadingProgressVisible)
    }

    @Test
    fun fail() = runBlocking {
        Mockito.`when`(getSuggestedActivityUseCase.execute()).thenThrow(MockitoException(""))

        store.process(IdeaStore.Action.LoadContent)

        assertEquals(null, store.stateObservable.stateValue.idea)
        assertEquals(false, store.stateObservable.stateValue.isIdeaTextVisible)

        assertEquals(ERROR, store.stateObservable.stateValue.errorText)
        assertEquals(true, store.stateObservable.stateValue.isErrorTextVisible)

        assertEquals(RETRY, store.stateObservable.stateValue.retryButtonText)
        assertEquals(true, store.stateObservable.stateValue.isRetryButtonVisible)

        assertEquals(null, store.stateObservable.stateValue.saveButtonText)
        assertEquals(false, store.stateObservable.stateValue.isSaveButtonVisible)
        assertEquals(false, store.stateObservable.stateValue.isSaveButtonClickable)

        assertEquals(null, store.stateObservable.stateValue.nextButtonText)
        assertEquals(false, store.stateObservable.stateValue.isNextButtonVisible)
        assertEquals(false, store.stateObservable.stateValue.isNextButtonClickable)

        assertEquals(false, store.stateObservable.stateValue.isLoadingProgressVisible)
    }
}