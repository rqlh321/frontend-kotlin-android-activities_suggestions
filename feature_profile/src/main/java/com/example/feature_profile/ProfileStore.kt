package com.example.feature_profile

import com.example.audit.Logger
import ru.gubatenko.domain.model.Pref
import ru.gubatenko.mvi.AbstractStore
import ru.gubatenko.mvi.SideEffects
import ru.gubatenko.mvi.StateObservable

class ProfileStore(
    logger: Logger,
    sideEffects: SideEffects<Action, SideAction>,
    stateObservable: StateObservable<State>,
) : AbstractStore<ProfileStore.Action, ProfileStore.SideAction, ProfileStore.State>(
    logger = logger,
    stateObservable = stateObservable,
    reducer = ProfileStoreReducer(),
    sideEffects = sideEffects
) {

    sealed class Action {
        object LoadProfile : Action()
        object ClickOnSignIn : Action()
        object ClickOnSignOut : Action()
        data class SwitchPref(
            val id: String,
            val isOn: Boolean
        ) : Action()
    }

    sealed class Event {
        object NavigateToAuthFlow : Event()
        data class ChangeAppThem(val isDarkThemEnabled: Boolean) : Event()
    }

    sealed class SideAction {
        data class SetupProfileScreen(
            val pref: List<Pref>,
            val name: String?,
            val email: String?,
            val avatar: String?,
            val signInButtonText: String,
            val signOutButtonText: String,
            val isSignInButtonVisible: Boolean,
            val isSignOutButtonVisible: Boolean,
        ) : SideAction()

        data class LogOut(val name: String) : SideAction()
        data class UpdatePrefs(val prefs: List<Pref>) : SideAction()
    }

    data class State(
        val name: String? = null,
        val avatar: String? = null,
        val email: String? = null,

        val prefs: List<Pref> = emptyList(),

        val signInButtonText: String? = null,
        val isSignInButtonVisible: Boolean = false,

        val signOutButtonText: String? = null,
        val isSignOutButtonVisible: Boolean = false,
    )
}
