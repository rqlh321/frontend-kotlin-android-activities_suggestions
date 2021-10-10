package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.Preference
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetProfilePrefsUseCase
import ru.gubatenko.domain.usecase.GetSignedInUserUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.mvi.SideEffect

class SetupProfileScreenSideEffect(
    private val getSignedInUserUseCase: GetSignedInUserUseCase,
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val getProfilePrefsUseCase: GetProfilePrefsUseCase,
) : SideEffect<ProfileStore.Action.OpenProfileScreen, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.OpenProfileScreen::class.java

    override suspend fun execute(
        action: ProfileStore.Action.OpenProfileScreen,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        val user = getSignedInUserUseCase.execute()

        val signInButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_IN)
        val signOutButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_OUT)
        val isSignedIn = user != null
        val name = user?.name ?: getStaticTextUseCase.execute(TextKey.Profile.DEFAULT_NAME)
        val email = user?.email
        val pref = getProfilePrefsUseCase.execute(user)

        reducerCallback.invoke(
            ProfileStore.SideAction.SetupProfileScreen(
                name = name,
                email = email,
                avatar = user?.avatar,
                pref = pref,
                signInButtonText = signInButtonText,
                signOutButtonText = signOutButtonText,
                isSignInButtonVisible = !isSignedIn,
                isSignOutButtonVisible = isSignedIn,
            )
        )
    }
}
