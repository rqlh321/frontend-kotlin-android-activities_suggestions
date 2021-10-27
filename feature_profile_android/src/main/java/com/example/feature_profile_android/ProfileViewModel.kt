package com.example.feature_profile_android

import com.example.feature_profile.ProfileStore
import ru.gubatenko.common_android.BaseViewModel
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataEventDispatcher
import ru.gubatenko.mvi_android.android.LiveDataStateObservable

class ProfileViewModel(
    dispatcher: EventDispatcher<ProfileStore.Event>,
    private val store: ProfileStore
) : BaseViewModel() {

    val state = (store.stateObservable as LiveDataStateObservable)
    val event = (dispatcher as LiveDataEventDispatcher<ProfileStore.Event>)

    init {
        load()
    }

    private fun load() = io {
        store.process(ProfileStore.Action.LoadProfile)
    }

    fun switchPref(id: String, isOn: Boolean) = default {
        store.process(ProfileStore.Action.SwitchPref(id, isOn))
    }

    fun signIn() = io {
        store.process(ProfileStore.Action.ClickOnSignIn)
    }

    fun signOut() = io {
        store.process(ProfileStore.Action.ClickOnSignOut)
    }

    fun successAuthorization() {
        load()
    }

}
