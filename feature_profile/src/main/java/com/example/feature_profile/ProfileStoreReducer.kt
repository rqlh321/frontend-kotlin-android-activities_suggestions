package com.example.feature_profile

import ru.gubatenko.mvi.Reducer

class ProfileStoreReducer : Reducer<ProfileStore.State, ProfileStore.SideAction> {
    override fun invoke(
        currentState: ProfileStore.State,
        newAction: ProfileStore.SideAction
    ): ProfileStore.State = when (newAction) {
        is ProfileStore.SideAction.SetupProfileScreen -> currentState.copy(
            name = newAction.name,
            avatar = newAction.avatar,
            about = newAction.aboutText,
            isSignOutButtonVisible = newAction.isSignOutButtonVisible,
            isSignInButtonVisible = newAction.isSignInButtonVisible,
            signInButtonText = newAction.signInButtonText,
            signOutButtonText = newAction.signOutButtonText,
        )
        is ProfileStore.SideAction.LogOut -> currentState.copy(
            name = null,
            avatar = null,
            isSignInButtonVisible = true,
            isSignOutButtonVisible = false
        )
    }
}
