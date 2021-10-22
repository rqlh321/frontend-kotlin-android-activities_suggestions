package com.example.feature_accepted_activities_android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.audit.Logger
import com.example.audit.LoggerStub
import com.example.feature_accepted_activities.PromiseStore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject
import org.mockito.Mockito
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class PromiseViewModelUnitTest : KoinComponent {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val getAllPromiseUseCase: GetAllPromiseUseCase by inject(GetAllPromiseUseCase::class.java)
    private val getStaticTextUseCase: GetStaticTextUseCase by inject(GetStaticTextUseCase::class.java)

    private val store: PromiseStore by inject(PromiseStore::class.java)

    @Before
    fun setup(): Unit = runBlocking {
        startKoin {
            modules(
                module {
                    single<Logger> { LoggerStub() }
                    single<GetAllPromiseUseCase> { Mockito.mock(GetAllPromiseUseCase::class.java) }
                    single<GetStaticTextUseCase> { Mockito.mock(GetStaticTextUseCase::class.java) }
                },
                promiseFeatureAndroidModuleDI
            )
        }
        Mockito
            .`when`(getStaticTextUseCase.execute(TextKey.Promise.EMPTY_LIST))
            .thenReturn(EMPTY_LIST_TEXT)
    }

    @After
    fun shutdown() {
        stopKoin()
    }

    @Test
    fun loadContentIsNotEmpty() = runBlocking {
        Mockito.`when`(getAllPromiseUseCase.execute())
            .thenReturn(ideaFlow)

        store.process(PromiseStore.Action.LoadContent)
        assertEquals(true, store.stateObservable.stateValue.isPromiseListVisible)
        assertEquals(false, store.stateObservable.stateValue.isInfoTextVisible)
        assertEquals(EMPTY_LIST_TEXT, store.stateObservable.stateValue.infoText)
        assertEquals(ideas.size, store.stateObservable.stateValue.promiseList.size)
    }

    @Test
    fun loadContentIsEmpty() = runBlocking {
        Mockito.`when`(getAllPromiseUseCase.execute())
            .thenReturn(emptyIdeaFlow)

        store.process(PromiseStore.Action.LoadContent)
        assertEquals(false, store.stateObservable.stateValue.isPromiseListVisible)
        assertEquals(true, store.stateObservable.stateValue.isInfoTextVisible)
        assertEquals(EMPTY_LIST_TEXT, store.stateObservable.stateValue.infoText)
        assertEquals(0, store.stateObservable.stateValue.promiseList.size)
    }

}