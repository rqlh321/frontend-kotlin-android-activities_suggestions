package com.example.feature_accepted_activities_android

import kotlinx.coroutines.flow.flow
import ru.gubatenko.domain.model.Idea

const val EMPTY_LIST_TEXT = "EMPTY_LIST_TEXT"
const val IDEA_ACTIVITY = "activity"
const val IDEA_TYPE = "type"
const val IDEA_ACCESSIBILITY = "accessibility"
const val IDEA_IS_SYNCED = false
const val IDEA_UID = Long.MAX_VALUE

val ideas = listOf(
    Idea(
        uid = IDEA_UID,
        activity = IDEA_ACTIVITY,
        type = IDEA_TYPE,
        accessibility = IDEA_ACCESSIBILITY,
        isSynced = IDEA_IS_SYNCED,
    )
)

val ideaFlow = flow { emit(ideas) }
val emptyIdeaFlow = flow<List<Idea>> { emit(emptyList()) }
val throwExceptionIdeaFlow = flow<List<Idea>> { throw Exception() }
