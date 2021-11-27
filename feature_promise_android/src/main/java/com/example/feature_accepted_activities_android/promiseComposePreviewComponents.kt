package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.PromiseStore
import ru.gubatenko.domain.model.Idea

private enum class CurrentState(val value: PromiseStore.State) {
    SUCCESS(
        PromiseStore.State(
            promiseList = listOf<Idea>(
                Idea(activity = "1"),
                Idea(activity = "2"),
                Idea(activity = "3"),
            ),
            isPromiseListVisible = true,
            infoText = null,
            isInfoTextVisible = false
        )
    )
}

fun previewState() = CurrentState.SUCCESS.value