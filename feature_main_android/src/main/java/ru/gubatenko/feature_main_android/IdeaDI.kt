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

private const val IDEA_STATE_OBSERVABLE = "IDEA_STATE_OBSERVABLE"
private const val IDEA_EVENT_DISPATCHER = "IDEA_EVENT_DISPATCHER"
private const val IDEA_SIDE_EFFECTS = "IDEA_SIDE_EFFECTS"
val ideaFeatureAndroidModuleDI = module {
    single<StateObservable<MainStore.State>> (named(IDEA_STATE_OBSERVABLE)){ LiveDataStateObservable(MainStore.State()) }
    single<EventDispatcher<MainStore.Event>> (named(IDEA_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single (named(IDEA_SIDE_EFFECTS)){
        SideEffects.Builder<MainStore.Action, MainStore.SideAction>()
            .append(sideEffect = LoadMainContentSideEffect(getStaticTextUseCase = get(), getSuggestedActivityUseCase = get()))
            .append(sideEffect = ClickOnSaveSideEffect(useCase = get(), stateObservable = get(named(IDEA_STATE_OBSERVABLE))))
            .build()
    }
    single {
        MainStore(
            logger = get(),
            sideEffects = get(named(IDEA_SIDE_EFFECTS)),
            stateObservable = get(named(IDEA_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        IdeaViewModel(
            dispatcher = get(named(IDEA_EVENT_DISPATCHER)),
            store = get(),
        )
    }
}
