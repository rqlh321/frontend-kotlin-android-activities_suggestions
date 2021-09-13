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
    private val getDynamicTextUseCase: GetDynamicTextUseCase,
) : SideEffect<ProfileStore.Action.InitProfileScreen, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.InitProfileScreen::class.java

    override suspend fun execute(
        action: ProfileStore.Action.InitProfileScreen,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        val user = getSignedInUserUseCase.execute()

        reducerCallback.invoke(
            ProfileStore.SideAction.SetupProfileScreen(
                name = user?.name,
                avatar = user?.avatar,
                aboutText = getDynamicTextUseCase.execute(TextKey.Dynamic.ABOUT),
                signInButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_IN),
                signOutButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_OUT),
                isSignInButtonVisible = false,
                isSignOutButtonVisible = true,
            )
        )
    }
}
