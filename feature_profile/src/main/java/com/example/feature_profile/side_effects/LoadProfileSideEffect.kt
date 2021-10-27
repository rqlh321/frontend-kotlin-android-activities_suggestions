package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetProfilePrefsUseCase
import ru.gubatenko.domain.usecase.GetSignedInUserUseCase
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.mvi.SideEffect

class LoadProfileSideEffect(
    private val getSignedInUserUseCase: GetSignedInUserUseCase,
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val getProfilePrefsUseCase: GetProfilePrefsUseCase,
) : SideEffect<ProfileStore.Action.LoadProfile, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.LoadProfile::class.java

    override suspend fun execute(
        action: ProfileStore.Action.LoadProfile,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        val user = getSignedInUserUseCase.execute()
        val isSignedIn = user != null
        val name = user?.name ?: getStaticTextUseCase.execute(TextKey.Profile.DEFAULT_NAME)
        val email = user?.email

        val signInButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_IN)
        val signOutButtonText = getStaticTextUseCase.execute(TextKey.Profile.SIGN_OUT)

        val pref = getProfilePrefsUseCase.execute()

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
