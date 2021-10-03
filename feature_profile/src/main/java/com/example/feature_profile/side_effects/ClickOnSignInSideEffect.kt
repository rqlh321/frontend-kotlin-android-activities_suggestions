package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class ClickOnSignInSideEffect(
    private val eventDispatcher: EventDispatcher<ProfileStore.Event>
) : SideEffect<ProfileStore.Action.ClickOnSignIn, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.ClickOnSignIn::class.java

    override suspend fun execute(
        action: ProfileStore.Action.ClickOnSignIn,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        eventDispatcher.dispatch(ProfileStore.Event.NavigateToAuthFlow)
    }
}
