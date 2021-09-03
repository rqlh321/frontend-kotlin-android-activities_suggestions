package com.example.feature_profile

import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class ProfileStore(
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<ProfileStore.Action, ProfileStore.SideAction, ProfileStore.State>(
    stateObservable = stateObservable,
    reducer = ProfileStoreReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object InitProfileScreen : Action()
        object ClickOnSignIn : Action()
        object ClickOnSignOut : Action()
    }

    sealed class Event {
        object NavigateToAuthFlow : Event()
    }

    sealed class SideAction {
        data class SetupCurrentUserState(
            val name: String?,
            val avatar: String?,
            val isSignInButtonVisible:Boolean,
            val isSignOutButtonVisible:Boolean,
        ) : SideAction()
    }

    data class State(
        val name: String? = null,
        val avatar: String? = null,

        val signInButtonText: String? = null,
        val isSignInButtonVisible: Boolean = false,

        val signOutButtonText: String? = null,
        val isSignOutButtonVisible: Boolean = false,
    )
}