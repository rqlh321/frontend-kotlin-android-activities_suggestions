package ru.gubatenko.feature_main

import ru.gubatenko.mvi.Reducer

class IdeaStoreReducer : Reducer<IdeaStore.State, IdeaStore.SideAction> {
    override fun invoke(
        currentState: IdeaStore.State,
        newAction: IdeaStore.SideAction
    ): IdeaStore.State = when (newAction) {
        is IdeaStore.SideAction.SavingStart -> currentState.copy(
            isSaveButtonClickable = false,
        )
        is IdeaStore.SideAction.LoadStart -> currentState.copy(
            isLoadingProgressVisible = true,

            isRetryButtonVisible = false,

            isSaveButtonClickable = false,
            isNextButtonClickable = false,

            isIdeaTextVisible = false,
            idea = null,

            isErrorTextVisible = false,
            errorText = null,
        )
        is IdeaStore.SideAction.LoadError -> currentState.copy(
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
        is IdeaStore.SideAction.LoadSuccess -> currentState.copy(
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
