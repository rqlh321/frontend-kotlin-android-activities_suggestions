package ru.gubatenko.feature_main_android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.core_android.android.LiveDataEventDispatcher
import ru.gubatenko.core_android.android.LiveDataStateObservableFactory
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.feature_main.side_effects.ClickOnMainContentSideEffect
import ru.gubatenko.feature_main.side_effects.ClickOnSaveSideEffect
import ru.gubatenko.feature_main.side_effects.LoadMainContentSideEffect
import ru.gubatenko.feature_main.side_effects.RefreshMainContentSideEffect

val mainFeatureDi = module {
    single<EventDispatcher<MainStore.Event>> {
        LiveDataEventDispatcher()
    }
    single {
        SideEffects.Builder<MainStore.Action, MainStore.SideAction>()
            .appendSideEffect(sideEffect = LoadMainContentSideEffect(useCase = get()))
            .appendSideEffect(sideEffect = ClickOnMainContentSideEffect(eventDispatcher = get()))
            .appendSideEffect(sideEffect = ClickOnSaveSideEffect(useCase = get()))
            .appendSideEffect(
                sideEffect = RefreshMainContentSideEffect(
                    eventDispatcher = get(),
                    useCase = get()
                )
            )
            .build()

    }
    single {
        MainStore(
            sideEffects = get(),
            stateObservableFactory = LiveDataStateObservableFactory(),
        )
    }
    viewModel {
        MainViewModel(
            dispatcher = get(),
            store = get(),
        )
    }
}