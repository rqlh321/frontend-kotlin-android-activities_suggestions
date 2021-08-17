package ru.gubatenko.feature_main

import ru.gubatenko.core.AbstractStore
import ru.gubatenko.core.SideEffects
import ru.gubatenko.core.StateObservable
import ru.gubatenko.domain.model.Greeting

class MainStore(
    sideEffects: SideEffects<Action, SideAction>,
    stateObservableFactory: StateObservable.Factory,
) : AbstractStore<MainStore.Action, MainStore.SideAction, MainStore.State>(
    initialState = State(),
    stateObservableFactory = stateObservableFactory,
    reducer = MainStoreReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object LoadContent : Action()
        object ClickOnContent : Action()
    }

    sealed class Event {
        object ShowToast : Event()
    }

    sealed class SideAction {
        object LoadStart : SideAction()
        object LoadError : SideAction()
        data class LoadSuccess(val greetings: List<Greeting>) : SideAction()
    }

    data class State(
        val contentValue: List<Greeting> = emptyList(),

        val contentVisible: Boolean = false,
        val errorVisible: Boolean = false,
        val loadingProgressVisible: Boolean = false,
    )
}