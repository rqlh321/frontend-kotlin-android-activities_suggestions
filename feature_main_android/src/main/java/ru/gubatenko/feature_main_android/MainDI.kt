package ru.gubatenko.feature_main_android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.feature_main.side_effects.ClickOnMainContentSideEffect
import ru.gubatenko.feature_main.side_effects.ClickOnSaveSideEffect
import ru.gubatenko.feature_main.side_effects.LoadMainContentSideEffect
import ru.gubatenko.feature_main.side_effects.RefreshMainContentSideEffect
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

val mainFeatureAndroidModuleDI = module {
    single<StateObservable<MainStore.State>> { LiveDataStateObservable(MainStore.State()) }
    single<EventDispatcher<MainStore.Event>> { LiveDataEventDispatcher() }

    single {
        SideEffects.Builder<MainStore.Action, MainStore.SideAction>()
            .appendSideEffect(sideEffect = LoadMainContentSideEffect(useCase = get(), state = get()))
            .appendSideEffect(sideEffect = ClickOnMainContentSideEffect(eventDispatcher = get()))
            .appendSideEffect(sideEffect = ClickOnSaveSideEffect(useCase = get(), state = get()))
            .appendSideEffect(sideEffect = RefreshMainContentSideEffect(eventDispatcher = get(), useCase = get()))
            .build()

    }
    single {
        MainStore(
            sideEffects = get(),
            stateObservable = get(),
        )
    }
    viewModel {
        MainViewModel(
            dispatcher = get(),
            store = get(),
        )
    }
}