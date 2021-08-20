package ru.gubatenko.feature_main

import ru.gubatenko.core.AbstractStore
import ru.gubatenko.core.SideEffects
import ru.gubatenko.core.StateObservable
import ru.gubatenko.domain.model.Activity

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
        object RefreshContent : Action()
    }

    sealed class Event {
        data class ShowToast(val message: String) : Event()
    }

    sealed class SideAction {
        object RefreshStart : SideAction()
        object RefreshError : SideAction()
        object LoadStart : SideAction()
        data class LoadError(
            val retryButtonText: String,
            val errorMessageText: String,
        ) : SideAction()

        data class LoadSuccess(val activity: Activity) : SideAction()
    }

    data class State(
        val actionText: String? = null,
        val isActionTextVisible: Boolean = false,

        val errorText: String? = null,
        val isErrorTextVisible: Boolean = false,

        val retryButtonText: String? = null,
        val isRetryButtonVisible: Boolean = false,

        val isLoadingProgressVisible: Boolean = false,
        val isRefreshProgressVisible: Boolean = false,
        val isRefreshEnabled: Boolean = false,
    )
}