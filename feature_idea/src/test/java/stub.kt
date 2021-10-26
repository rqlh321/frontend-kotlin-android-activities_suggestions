package com.example.feature_accepted_activities_android

import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.model.Idea
import ru.gubatenko.feature_main.IDEA_EVENT_DISPATCHER
import ru.gubatenko.feature_main.IDEA_STATE_OBSERVABLE
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi.StubEventDispatcher
import ru.gubatenko.mvi.StubStateObservable

const val SAVE = "SAVE"
const val NEXT = "NEXT"
const val ERROR = "ERROR"
const val RETRY = "RETRY"

const val IDEA_ACTIVITY = "activity"
const val IDEA_TYPE = "type"
const val IDEA_ACCESSIBILITY = "accessibility"
const val IDEA_IS_SYNCED = false
const val IDEA_UID = Long.MAX_VALUE

val idea =  Idea(
    uid = IDEA_UID,
    activity = IDEA_ACTIVITY,
    type = IDEA_TYPE,
    accessibility = IDEA_ACCESSIBILITY,
    isSynced = IDEA_IS_SYNCED,
)

val stubStateEvent = module {
    single<StateObservable<IdeaStore.State>>(named(IDEA_STATE_OBSERVABLE)) { StubStateObservable(IdeaStore.State()) }
    single<EventDispatcher<IdeaStore.Event>>(named(IDEA_EVENT_DISPATCHER)) { StubEventDispatcher() }
}
