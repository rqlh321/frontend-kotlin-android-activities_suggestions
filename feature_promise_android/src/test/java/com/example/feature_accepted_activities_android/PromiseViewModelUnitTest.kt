package com.example.feature_accepted_activities_android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.audit.Logger
import com.example.audit.LoggerStub
import com.example.feature_accepted_activities.PromiseStore
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
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
import ru.gubatenko.domain.model.Idea
import ru.gubatenko.domain.usecase.GetAllPromiseUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase

class PromiseViewModelUnitTest : KoinComponent {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val ideaFlow = flow {
        emit(
            listOf(
                Idea(
                    uid = Long.MAX_VALUE,
                    activity = "activity",
                    type = "type",
                    accessibility = "accessibility",
                    isSynced = false,
                )
            )
        )
    }

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
            .thenReturn("")

        Mockito
            .`when`(getAllPromiseUseCase.execute())
            .thenReturn(ideaFlow)
    }

    @After
    fun shutdown() {
        stopKoin()
    }

    @Test
    fun init() = runBlocking {
        store.process(PromiseStore.Action.LoadContent)
        assertEquals(1, store.stateObservable.stateValue.promiseList.size)
    }

}