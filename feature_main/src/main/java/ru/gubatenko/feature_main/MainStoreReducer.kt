package ru.gubatenko.feature_main

import ru.gubatenko.core.Reducer

class MainStoreReducer : Reducer<MainStore.State, MainStore.SideAction> {
    override fun invoke(
        currentState: MainStore.State,
        newAction: MainStore.SideAction
    ): MainStore.State = when (newAction) {
        is MainStore.SideAction.LoadStart -> currentState.copy(
            isLoadingProgressVisible = true,
            isRefreshProgressVisible = false,
            isRefreshEnabled = false,

            isRetryButtonVisible = false,
            retryButtonText = null,

            isActionTextVisible = false,
            actionText = null,

            isErrorTextVisible = false,
            errorText = null,
        )
        is MainStore.SideAction.RefreshStart -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshProgressVisible = true,

            isRetryButtonVisible = false,
            retryButtonText = null,

            isErrorTextVisible = false,
            errorText = null,
        )
        is MainStore.SideAction.LoadError -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshProgressVisible = false,
            isRefreshEnabled = false,

            isRetryButtonVisible = true,
            retryButtonText = newAction.retryButtonText,

            isActionTextVisible = false,
            actionText = null,

            isErrorTextVisible = true,
            errorText = newAction.errorMessageText,
        )
        is MainStore.SideAction.RefreshError -> currentState.copy(
            isRefreshProgressVisible = false,
        )
        is MainStore.SideAction.LoadSuccess -> currentState.copy(
            isLoadingProgressVisible = false,
            isRefreshProgressVisible = false,
            isRefreshEnabled = true,

            isRetryButtonVisible = false,
            retryButtonText = null,

            isActionTextVisible = true,
            actionText = newAction.activity.activity,

            isErrorTextVisible = false,
            errorText = null,
        )
    }
}
