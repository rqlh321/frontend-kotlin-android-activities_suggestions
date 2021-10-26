package com.example.feature_accepted_activities_android

import com.example.feature_accepted_activities.PROMISE_EVENT_DISPATCHER
import com.example.feature_accepted_activities.PROMISE_STATE_OBSERVABLE
import com.example.feature_accepted_activities.PromiseStore
import kotlinx.coroutines.flow.flow
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.model.Idea
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi.StubEventDispatcher
import ru.gubatenko.mvi.StubStateObservable

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

val stubStateEvent = module {
    single<StateObservable<PromiseStore.State>>(named(PROMISE_STATE_OBSERVABLE)) { StubStateObservable(PromiseStore.State()) }
    single<EventDispatcher<PromiseStore.Event>>(named(PROMISE_EVENT_DISPATCHER)) { StubEventDispatcher() }
}
