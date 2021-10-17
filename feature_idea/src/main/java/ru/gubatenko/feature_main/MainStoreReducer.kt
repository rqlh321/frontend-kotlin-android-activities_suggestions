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

            isRetryButtonVisible = false,

            isSaveButtonClickable = false,
            isNextButtonClickable = false,

            isIdeaTextVisible = false,
            idea = null,

            isErrorTextVisible = false,
            errorText = null,
        )
        is MainStore.SideAction.LoadError -> currentState.copy(
            isLoadingProgressVisible = false,

            retryButtonText = newAction.retryButtonText,
            isRetryButtonVisible = true,

            isSaveButtonVisible = false,
            isSaveButtonClickable = false,

            isIdeaTextVisible = false,
            idea = null,

            isErrorTextVisible = true,
            errorText = newAction.message,
        )
        is MainStore.SideAction.LoadSuccess -> currentState.copy(
            isLoadingProgressVisible = false,

            isRetryButtonVisible = false,

            saveButtonText = newAction.saveButtonText ?: currentState.saveButtonText,
            isSaveButtonVisible = true,
            isSaveButtonClickable = true,

            nextButtonText = newAction.nextButtonText ?: currentState.nextButtonText,
            isNextButtonVisible = true,
            isNextButtonClickable = true,

            isIdeaTextVisible = true,
            idea = newAction.idea,

            isErrorTextVisible = false,
            errorText = null,
        )
    }
}
