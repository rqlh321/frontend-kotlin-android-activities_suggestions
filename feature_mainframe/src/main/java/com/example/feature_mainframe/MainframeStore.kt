package com.example.feature_mainframe

import com.example.audit.Logger
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class MainframeStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<MainframeStore.Action, MainframeStore.SideAction, MainframeStore.State>(
    logger = logger,
    stateObservable = stateObservable,
    reducer = MainframeReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object CheckSettingsAction : Action()
    }

    sealed class Event

    sealed class SideAction {
        data class SetupSideAction(
            val isDarkModeOn: Boolean
        ) : SideAction()
    }

    data class State(
        val isDarkModeOn: Boolean = false
    )
}
