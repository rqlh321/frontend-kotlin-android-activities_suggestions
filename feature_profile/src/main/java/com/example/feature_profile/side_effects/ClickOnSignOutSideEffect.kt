package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.usecase.SignOutUseCase
import ru.gubatenko.mvi.SideEffect

class ClickOnSignOutSideEffect(
    private val useCase: SignOutUseCase
) : SideEffect<ProfileStore.Action.ClickOnSignOut, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.ClickOnSignOut::class.java

    override suspend fun execute(
        action: ProfileStore.Action.ClickOnSignOut,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        useCase.execute()
        reducerCallback.invoke(
            ProfileStore.SideAction.SetupCurrentUserState(
                name = null,
                avatar = null,
                isSignInButtonVisible = true,
                isSignOutButtonVisible = false
            )
        )
    }
}
