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
            retryButtonText = null,

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
            retryButtonText = null,

            isSaveButtonVisible = true,
            isSaveButtonClickable = true,

            isErrorTextVisible = false,
            errorText = null,
        )
        is MainStore.SideAction.LoadError -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshInProgress = false,
            isRefreshEnabled = false,

            isRetryButtonVisible = true,
            retryButtonText = newAction.retryButtonText,

            isSaveButtonVisible = false,
            isSaveButtonClickable = false,

            isActionTextVisible = false,
            action = null,

            isErrorTextVisible = true,
            errorText = newAction.errorMessageText,
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
            retryButtonText = null,

            isSaveButtonVisible = true,
            isSaveButtonClickable = true,

            saveButtonText = newAction.saveButtonText,

            isActionTextVisible = true,
            action = newAction.activity,

            isErrorTextVisible = false,
            errorText = null,
        )
    }
}
