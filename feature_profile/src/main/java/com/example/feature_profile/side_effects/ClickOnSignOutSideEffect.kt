package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.LongTermWorkUseCase
import ru.gubatenko.domain.usecase.SignOutUseCase
import ru.gubatenko.mvi.SideEffect

class ClickOnSignOutSideEffect(
    private val signOutUseCase: SignOutUseCase,
    private val longTermWorkUseCase: LongTermWorkUseCase,
    private val getStaticTextUseCase: GetStaticTextUseCase,
) : SideEffect<ProfileStore.Action.ClickOnSignOut, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.ClickOnSignOut::class.java

    override suspend fun execute(
        action: ProfileStore.Action.ClickOnSignOut,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        signOutUseCase.execute()
        longTermWorkUseCase.execute(LongTermWorkUseCase.Query.StopLoad)
        val name = getStaticTextUseCase.execute(TextKey.Profile.DEFAULT_NAME)
        reducerCallback.invoke(ProfileStore.SideAction.LogOut(name))
    }
}
