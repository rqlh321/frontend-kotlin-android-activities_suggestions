package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetDynamicTextUseCase
import ru.gubatenko.domain.usecase.GetSignedInUserUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.mvi.SideEffect

class GetCurrentUserSideEffect(
    private val getSignedInUserUseCase: GetSignedInUserUseCase,
    private val getStaticTextUseCase: GetStaticTextUseCase,
) : SideEffect<ProfileStore.Action.InitProfileScreen, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.InitProfileScreen::class.java

    override suspend fun execute(
        action: ProfileStore.Action.InitProfileScreen,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        val user = getSignedInUserUseCase.execute()

        val signInButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_IN)
        val signOutButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_OUT)
        reducerCallback.invoke(
            ProfileStore.SideAction.SetupProfileScreen(
                name = user?.name,
                avatar = user?.avatar,
                signInButtonText = signInButtonText,
                signOutButtonText = signOutButtonText,
                isSignInButtonVisible = false,
                isSignOutButtonVisible = true,
            )
        )
    }
}
