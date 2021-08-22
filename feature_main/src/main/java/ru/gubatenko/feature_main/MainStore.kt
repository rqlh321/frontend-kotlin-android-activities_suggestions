package ru.gubatenko.feature_main

import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
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
        data class SaveContent(
            val action: Activity?
        ) : Action()
    }

    sealed class Event {
        data class ShowToast(val message: String) : Event()
        data class NavigateTo(val locationId: Int) : Event()
    }

    sealed class SideAction {
        object RefreshStart : SideAction()
        object RefreshError : SideAction()
        data class RefreshSuccess(
            val activity: Activity,
        ) : SideAction()

        object LoadStart : SideAction()
        data class LoadError(
            val retryButtonText: String,
            val errorMessageText: String,
        ) : SideAction()

        data class LoadSuccess(
            val saveButtonText: String,
            val activity: Activity,
        ) : SideAction()
    }

    data class State(
        val action: Activity? = null,
        val isActionTextVisible: Boolean = false,

        val errorText: String? = null,
        val isErrorTextVisible: Boolean = false,

        val retryButtonText: String? = null,
        val isRetryButtonVisible: Boolean = false,

        val saveButtonText: String? = null,
        val isSaveButtonVisible: Boolean = false,

        val isLoadingProgressVisible: Boolean = false,
        val isRefreshProgressVisible: Boolean = false,
        val isRefreshEnabled: Boolean = false,
    )
}