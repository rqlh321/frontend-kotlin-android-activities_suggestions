package ru.gubatenko.feature_main_android

import ru.gubatenko.domain.model.Idea
import ru.gubatenko.feature_main.IdeaStore

private enum class CurrentState(val value: IdeaStore.State) {
    SUCCESS(
        IdeaStore.State(
            idea = Idea(activity = "Test activity"),
            isIdeaTextVisible = true,
            saveButtonText = "Save",
            isSaveButtonVisible = true,
            isSaveButtonClickable = true,
            nextButtonText = "Next",
            isNextButtonVisible = true,
            isNextButtonClickable = true,
        )
    ),
    ERROR(
        IdeaStore.State(
            errorText = "Error text",
            isErrorTextVisible = true,
            isSaveButtonClickable = true,
            retryButtonText = "Retry",
            isRetryButtonVisible = true,
        )
    )
}

fun previewState() = CurrentState.SUCCESS.value