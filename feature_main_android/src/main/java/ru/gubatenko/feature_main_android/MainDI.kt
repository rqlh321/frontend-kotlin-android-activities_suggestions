package ru.gubatenko.feature_main_android

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.feature_main.side_effects.*
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val MAIN_STATE_OBSERVABLE = "MAIN_STATE_OBSERVABLE"
private const val MAIN_EVENT_DISPATCHER = "MAIN_EVENT_DISPATCHER"
private const val MAIN_SIDE_EFFECTS = "MAIN_SIDE_EFFECTS"
val mainFeatureAndroidModuleDI = module {
    single<StateObservable<MainStore.State>> (named(MAIN_STATE_OBSERVABLE)){ LiveDataStateObservable(MainStore.State()) }
    single<EventDispatcher<MainStore.Event>> (named(MAIN_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single (named(MAIN_SIDE_EFFECTS)){
        SideEffects.Builder<MainStore.Action, MainStore.SideAction>()
            .append(sideEffect = SetupMainScreenSideEffect(getStaticTextUseCase = get(), getSuggestedActivityUseCase = get()))
            .append(sideEffect = LoadMainContentSideEffect(getStaticTextUseCase = get(), stateObservable = get(named(MAIN_STATE_OBSERVABLE)), getSuggestedActivityUseCase = get()))
            .append(sideEffect = ClickOnMainContentSideEffect(eventDispatcher = get(named(MAIN_EVENT_DISPATCHER))))
            .append(sideEffect = ClickOnSaveSideEffect(useCase = get(), stateObservable = get(named(MAIN_STATE_OBSERVABLE))))
            .append(sideEffect = RefreshMainContentSideEffect(eventDispatcher = get(named(MAIN_EVENT_DISPATCHER)), getStaticTextUseCase = get(), getSuggestedActivityUseCase = get()))
            .build()
    }
    single {
        MainStore(
            sideEffects = get(named(MAIN_SIDE_EFFECTS)),
            stateObservable = get(named(MAIN_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        MainViewModel(
            dispatcher = get(named(MAIN_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}