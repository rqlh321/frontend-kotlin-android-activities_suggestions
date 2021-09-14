package ru.gubatenko.feature_main

import ru.gubatenko.mvi.Reducer

class MainStoreReducer : Reducer<MainStore.State, MainStore.SideAction> {
    override fun invoke(
        currentState: MainStore.State,
        newAction: MainStore.SideAction
    ): MainStore.State = when (newAction) {
        is MainStore.SideAction.SavingStart -> currentState.copy(
            isSaveButtonClickable = false,
        )
        is MainStore.SideAction.LoadStart -> currentState.copy(
            isLoadingProgressVisible = true,
            isRefreshInProgress = false,
            isRefreshEnabled = false,

            isRetryButtonVisible = false,

            isSaveButtonClickable = false,

            isActionTextVisible = false,
            action = null,

            isErrorTextVisible = false,
            errorText = null,
        )
        is MainStore.SideAction.RefreshStart -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshInProgress = true,

            isRetryButtonVisible = false,

            isSaveButtonVisible = true,
            isSaveButtonClickable = true,

            isErrorTextVisible = false,
            errorText = null,
        )
        is MainStore.SideAction.LoadError -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshInProgress = false,
            isRefreshEnabled = false,

            retryButtonText = newAction.retryButtonText,
            isRetryButtonVisible = true,

            isSaveButtonVisible = false,
            isSaveButtonClickable = false,

            isActionTextVisible = false,
            action = null,

            isErrorTextVisible = true,
            errorText = newAction.message,
        )
        is MainStore.SideAction.RefreshError -> currentState.copy(
            isRefreshInProgress = false,
        )
        is MainStore.SideAction.RefreshSuccess -> currentState.copy(
            action = newAction.activity,
            isRefreshInProgress = false,
        )
        is MainStore.SideAction.LoadSuccess -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshInProgress = false,
            isRefreshEnabled = true,

            isRetryButtonVisible = false,

            saveButtonText = newAction.saveButtonText ?: currentState.saveButtonText,
            isSaveButtonVisible = true,
            isSaveButtonClickable = true,

            isActionTextVisible = true,
            action = newAction.activity,

            isErrorTextVisible = false,
            errorText = null,
        )
    }
}
