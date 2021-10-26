package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.PromiseStore
import com.example.feature_accepted_activities.promiseStoreModuleDI
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
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class LoadPromisesUnitTest : KoinComponent {

    private val getAllPromiseUseCase: GetAllPromiseUseCase by inject(GetAllPromiseUseCase::class.java)
    private val getStaticTextUseCase: GetStaticTextUseCase by inject(GetStaticTextUseCase::class.java)

    private val store: PromiseStore by inject(PromiseStore::class.java)

    @Before
    fun setup(): Unit = runBlocking {
        startKoin {
            modules(
                mockedUseCase,
                stubStateEvent,
                promiseStoreModuleDI
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
    fun loadedContentIsNotEmpty() = runBlocking {
        Mockito.`when`(getAllPromiseUseCase.execute())
            .thenReturn(ideaFlow)

        store.process(PromiseStore.Action.LoadContent)
        assertEquals(true, store.stateObservable.stateValue.isPromiseListVisible)
        assertEquals(false, store.stateObservable.stateValue.isInfoTextVisible)
        assertEquals(EMPTY_LIST_TEXT, store.stateObservable.stateValue.infoText)
        assertEquals(true, store.stateObservable.stateValue.promiseList.isNotEmpty())
    }

    @Test
    fun loadedContentIsEmpty() = runBlocking {
        Mockito.`when`(getAllPromiseUseCase.execute())
            .thenReturn(emptyIdeaFlow)

        store.process(PromiseStore.Action.LoadContent)
        assertEquals(false, store.stateObservable.stateValue.isPromiseListVisible)
        assertEquals(true, store.stateObservable.stateValue.isInfoTextVisible)
        assertEquals(EMPTY_LIST_TEXT, store.stateObservable.stateValue.infoText)
        assertEquals(true, store.stateObservable.stateValue.promiseList.isEmpty())
    }

    @Test
    fun loadContentFail() = runBlocking {
        Mockito.`when`(getAllPromiseUseCase.execute())
            .thenReturn(throwExceptionIdeaFlow)

        store.process(PromiseStore.Action.LoadContent)
        assertEquals(false, store.stateObservable.stateValue.isPromiseListVisible)
        assertEquals(false, store.stateObservable.stateValue.isInfoTextVisible)
        assertEquals(null, store.stateObservable.stateValue.infoText)
        assertEquals(true, store.stateObservable.stateValue.promiseList.isEmpty())
    }
}