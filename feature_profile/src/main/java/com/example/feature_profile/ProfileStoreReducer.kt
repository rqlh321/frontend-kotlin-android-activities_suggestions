package com.example.feature_profile

import ru.gubatenko.mvi.Reducer

class ProfileStoreReducer : Reducer<ProfileStore.State, ProfileStore.SideAction> {
    override fun invoke(
        currentState: ProfileStore.State,
        newAction: ProfileStore.SideAction
    ): ProfileStore.State = when (newAction) {
        is ProfileStore.SideAction.SetupCurrentUserState -> currentState.copy(
            name = newAction.name,
            avatar = newAction.avatar,
            isSignOutButtonVisible = newAction.isSignOutButtonVisible,
            isSignInButtonVisible = newAction.isSignInButtonVisible,
        )
    }
}
