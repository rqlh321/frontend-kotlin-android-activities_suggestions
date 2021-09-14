package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.AcceptedActivitiesStore
import com.example.feature_accepted_activities.side_effects.ShowContentSideEffect
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

private const val ACTIVITIES_STATE_OBSERVABLE = "ACTIVITIES_STATE_OBSERVABLE"
private const val ACTIVITIES_EVENT_DISPATCHER = "ACTIVITIES_EVENT_DISPATCHER"
private const val ACTIVITIES_SIDE_EFFECTS = "ACTIVITIES_SIDE_EFFECTS"
val activitiesFeatureAndroidModuleDI = module {
    single<StateObservable<AcceptedActivitiesStore.State>> (named(ACTIVITIES_STATE_OBSERVABLE)) { LiveDataStateObservable(AcceptedActivitiesStore.State()) }
    single<EventDispatcher<AcceptedActivitiesStore.Event>> (named(ACTIVITIES_EVENT_DISPATCHER)) { LiveDataEventDispatcher() }

    single (named(ACTIVITIES_SIDE_EFFECTS)){
        SideEffects.Builder<AcceptedActivitiesStore.Action, AcceptedActivitiesStore.SideAction>()
            .append(sideEffect = ShowContentSideEffect(useCase = get()))
            .build()
    }
    single {
        AcceptedActivitiesStore(
            logger = get(),
            sideEffects = get(named(ACTIVITIES_SIDE_EFFECTS)),
            stateObservable = get(named(ACTIVITIES_STATE_OBSERVABLE)),
        )
    }
    viewModel {
        ActivitiesViewModel(
            store = get(),
        )
    }
}