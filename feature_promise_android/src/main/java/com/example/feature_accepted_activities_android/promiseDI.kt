package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.PROMISE_EVENT_DISPATCHER
import com.example.feature_accepted_activities.PROMISE_STATE_OBSERVABLE
import com.example.feature_accepted_activities.PromiseStore
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

val promiseFeatureAndroidModuleDI = module {
    single<StateObservable<PromiseStore.State>> (named(PROMISE_STATE_OBSERVABLE)) { LiveDataStateObservable(PromiseStore.State()) }
    single<EventDispatcher<PromiseStore.Event>> (named(PROMISE_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    viewModel {
        PromiseViewModel(
            store = get(),
        )
    }
}