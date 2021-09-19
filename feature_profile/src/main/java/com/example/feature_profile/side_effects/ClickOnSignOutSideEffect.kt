package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.usecase.SignOutUseCase
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class ClickOnSignOutSideEffect(
    private val useCase: SignOutUseCase,
    private val eventDispatcher: EventDispatcher<ProfileStore.Event>
) : SideEffect<ProfileStore.Action.ClickOnSignOut, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.ClickOnSignOut::class.java

    override suspend fun execute(
        action: ProfileStore.Action.ClickOnSignOut,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        useCase.execute()
        eventDispatcher.dispatch(ProfileStore.Event.StopSyncProcesses)
        reducerCallback.invoke(ProfileStore.SideAction.LogOut)
    }
}
