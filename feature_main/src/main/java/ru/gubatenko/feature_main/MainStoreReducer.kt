package ru.gubatenko.feature_main

import ru.gubatenko.core.Reducer

class MainStoreReducer : Reducer<MainStore.State, MainStore.SideAction> {
    override fun invoke(
        currentState: MainStore.State,
        newAction: MainStore.SideAction
    ): MainStore.State = when (newAction) {
        is MainStore.SideAction.LoadStart -> currentState.copy(
            loadingProgressVisible = true,
            contentVisible = false,
            errorVisible = false,
        )
        is MainStore.SideAction.LoadError -> currentState.copy(
            loadingProgressVisible = false,
            contentVisible = false,
            errorVisible = true,
        )
        is MainStore.SideAction.LoadSuccess -> currentState.copy(
            loadingProgressVisible = false,
            contentVisible = false,
            errorVisible = false,
            contentValue = newAction.greetings
        )
    }
}
