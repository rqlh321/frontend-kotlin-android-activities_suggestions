package ru.gubatenko.feature_main

import ru.gubatenko.domain.model.Activity
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable
import com.example.audit.Logger

class MainStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<MainStore.Action, MainStore.SideAction, MainStore.State>(
    logger = logger,
    stateObservable = stateObservable,
    reducer = MainStoreReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object SetupScreen : Action()
        object LoadContent : Action()
        object ClickOnContent : Action()
        object RefreshContent : Action()
        object SaveContent : Action()
    }

    sealed class Event {
        data class ShowToast(val message: String) : Event()
        data class NavigateTo(val locationId: Int) : Event()
        object NavigateToAuthFlow : Event()
    }

    sealed class SideAction {
        object RefreshStart : SideAction()
        object RefreshError : SideAction()
        data class RefreshSuccess(val activity: Activity) : SideAction()

        object LoadStart : SideAction()
        object SavingStart : SideAction()

        data class LoadError(val message: String, val retryButtonText: String) : SideAction()

        data class LoadSuccess(val activity: Activity, val saveButtonText: String? = null) : SideAction()
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
        val isSaveButtonClickable: Boolean = false,

        val isLoadingProgressVisible: Boolean = false,
        val isRefreshInProgress: Boolean = false,
        val isRefreshEnabled: Boolean = false,
    )
}