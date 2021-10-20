package com.example.feature_accepted_activities

import com.example.audit.Logger
import ru.gubatenko.domain.model.Idea
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class PromiseStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<PromiseStore.Action, PromiseStore.SideAction, PromiseStore.State>(
    logger = logger,
    stateObservable = stateObservable,
    reducer = PromiseStoreReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object LoadContent : Action()
    }

    sealed class Event

    sealed class SideAction {
        data class LoadSuccess(
            val promiseList: List<Idea>,
            val isPromiseListVisible: Boolean,
            val infoText: String,
            val isInfoTextVisible: Boolean
        ) : SideAction()
    }

    data class State(
        val promiseList: List<Idea> = emptyList(),
        val isPromiseListVisible: Boolean = false,
        val infoText: String? = null,
        val isInfoTextVisible: Boolean = false
    )
}