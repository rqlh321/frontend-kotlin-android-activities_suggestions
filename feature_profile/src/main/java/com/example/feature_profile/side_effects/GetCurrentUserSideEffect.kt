package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.usecase.GetSignedInUserUseCase
import ru.gubatenko.mvi.SideEffect

class GetCurrentUserSideEffect(
    private val useCase: GetSignedInUserUseCase
) : SideEffect<ProfileStore.Action.InitProfileScreen, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.InitProfileScreen::class.java

    override suspend fun execute(
        action: ProfileStore.Action.InitProfileScreen,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        val user = useCase.execute()
        if (user == null) {
            reducerCallback.invoke(
                ProfileStore.SideAction.SetupCurrentUserState(
                    name = null,
                    avatar = null,
                    isSignInButtonVisible = true,
                    isSignOutButtonVisible = false,
                )
            )
        } else {
            reducerCallback.invoke(
                ProfileStore.SideAction.SetupCurrentUserState(
                    name = user.name,
                    avatar = user.avatar,
                    isSignInButtonVisible = false,
                    isSignOutButtonVisible = true,
                )
            )
        }
    }
}
