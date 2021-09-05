package com.example.feature_accepted_activities

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class AcceptedActivitiesStore(
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<AcceptedActivitiesStore.Action, AcceptedActivitiesStore.SideAction, AcceptedActivitiesStore.State>(
    stateObservable = stateObservable,
    reducer = AcceptedActivitiesStoreReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object LoadContent : Action()
    }

    sealed class Event

    sealed class SideAction {
        data class LoadSuccess(val activities: List<Activity>) : SideAction()
    }

    data class State(val activities: List<Activity> = emptyList())
}