package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.PromiseStore
import com.example.feature_accepted_activities.side_effects.ShowContentSideEffect
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val PROMISE_STATE_OBSERVABLE = "PROMISE_STATE_OBSERVABLE"
private const val PROMISE_EVENT_DISPATCHER = "PROMISE_EVENT_DISPATCHER"
private const val PROMISE_SIDE_EFFECTS = "PROMISE_SIDE_EFFECTS"
val promiseFeatureAndroidModuleDI = module {
    single<StateObservable<PromiseStore.State>> (named(PROMISE_STATE_OBSERVABLE)) { LiveDataStateObservable(PromiseStore.State()) }
    single<EventDispatcher<PromiseStore.Event>> (named(PROMISE_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single (named(PROMISE_SIDE_EFFECTS)){
        SideEffects.Builder<PromiseStore.Action, PromiseStore.SideAction>()
            .append(sideEffect = ShowContentSideEffect(getAllPromiseUseCase = get(), getStaticTextUseCase = get()))
            .build()
    }
    single {
        PromiseStore(
            logger = get(),
            sideEffects = get(named(PROMISE_SIDE_EFFECTS)),
            stateObservable = get(named(PROMISE_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        PromiseViewModel(
            store = get(),
        )
    }
}