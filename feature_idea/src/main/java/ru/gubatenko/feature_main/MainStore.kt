package ru.gubatenko.feature_main

import com.example.audit.Logger
import ru.gubatenko.domain.model.Activity
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

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
        object LoadContent : Action()
        object SaveContent : Action()
    }

    sealed class Event {
        data class NavigateTo(
            val locationId: Int
        ) : Event()

        object NavigateToAuthFlow : Event()
    }

    sealed class SideAction {
        object LoadStart : SideAction()
        object SavingStart : SideAction()

        data class LoadError(
            val message: String,
            val retryButtonText: String
        ) : SideAction()

        data class LoadSuccess(
            val idea: Activity,
            val saveButtonText: String? = null,
            val nextButtonText: String? = null,
        ) : SideAction()
    }

    data class State(
        val idea: Activity? = null,
        val isIdeaTextVisible: Boolean = false,

        val errorText: String? = null,
        val isErrorTextVisible: Boolean = false,

        val retryButtonText: String? = null,
        val isRetryButtonVisible: Boolean = false,

        val saveButtonText: String? = null,
        val isSaveButtonVisible: Boolean = false,
        val isSaveButtonClickable: Boolean = false,

        val nextButtonText: String? = null,
        val isNextButtonVisible: Boolean = false,
        val isNextButtonClickable: Boolean = false,

        val isLoadingProgressVisible: Boolean = false,
    )
}
