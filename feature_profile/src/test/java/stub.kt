package com.example.feature_accepted_activities_android

import com.example.audit.Logger
import com.example.audit.LoggerStub
import com.example.feature_profile.PROFILE_EVENT_DISPATCHER
import com.example.feature_profile.PROFILE_STATE_OBSERVABLE
import com.example.feature_profile.ProfileStore
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gubatenko.domain.model.Pref
import ru.gubatenko.domain.model.SwitchPref
import ru.gubatenko.domain.model.User
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.StateObservable
import ru.gubatenko.mvi.StubEventDispatcher
import ru.gubatenko.mvi.StubStateObservable

const val DEFAULT_NAME = "DEFAULT_NAME"
const val SIGN_IN = "SIGN_IN"
const val SIGN_OUT = "SIGN_OUT"

const val USER_UID = "USER_UID"
const val USER_NAME = "USER_NAME"
const val USER_EMAIL = "USER_EMAIL"
const val USER_AVATAR = "USER_AVATAR"

val user = User(
    uid = USER_UID,
    name = USER_NAME,
    email = USER_EMAIL,
    avatar = USER_AVATAR,
)

const val PREF_ID = "PREF_ID"
const val PREF_TITLE = "PREF_TITLE"
const val PREF_IS_ON = false

val prefs = listOf<Pref>(
    SwitchPref(
        id = PREF_ID,
        title = PREF_TITLE,
        isOn = PREF_IS_ON,
    )
)
val stubStateEvent = module {
    single<Logger> { LoggerStub() }
    single<StateObservable<ProfileStore.State>>(named(PROFILE_STATE_OBSERVABLE)) { StubStateObservable(ProfileStore.State()) }
    single<EventDispatcher<ProfileStore.Event>>(named(PROFILE_EVENT_DISPATCHER)) { StubEventDispatcher() }
}
