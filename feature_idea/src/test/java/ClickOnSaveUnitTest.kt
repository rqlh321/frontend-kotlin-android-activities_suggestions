package com.example.feature_accepted_activities_android

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
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.domain.usecase.SaveActivityToLocalStorageUseCase
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.feature_main.ideaStoreModuleDI

class ClickOnSaveUnitTest : KoinComponent {

    private val saveActivityToLocalStorageUseCase: SaveActivityToLocalStorageUseCase by inject(SaveActivityToLocalStorageUseCase::class.java)
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
        Mockito.`when`(saveActivityToLocalStorageUseCase.execute(idea)).thenReturn(Unit)
        Mockito.`when`(getSuggestedActivityUseCase.execute()).thenReturn(idea)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Main.SAVE)).thenReturn(SAVE)
        Mockito.`when`(getStaticTextUseCase.execute(TextKey.Main.NEXT)).thenReturn(NEXT)
    }

    @After
    fun shutdown() {
        stopKoin()
    }

    @Test
    fun click() = runBlocking {
        store.process(IdeaStore.Action.LoadContent)
        store.process(IdeaStore.Action.SaveContent)
    }

}