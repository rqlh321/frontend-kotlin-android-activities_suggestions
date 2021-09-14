package com.example.feature_accepted_activities

import com.example.audit.Logger
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class AcceptedActivitiesStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<AcceptedActivitiesStore.Action, AcceptedActivitiesStore.SideAction, AcceptedActivitiesStore.State>(
    logger = logger,
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