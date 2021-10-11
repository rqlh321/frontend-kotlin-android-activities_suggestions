package com.example.feature_profile.side_effects

import com.example.feature_profile.ProfileStore
import kotlinx.coroutines.delay
import ru.gubatenko.domain.usecase.GetProfilePrefsUseCase
import ru.gubatenko.domain.usecase.SetPrefUseCase
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class SwitchPrefSideEffect(
    private val eventDispatcher: EventDispatcher<ProfileStore.Event>,
    private val setPrefUseCase: SetPrefUseCase,
    private val getProfilePrefsUseCase: GetProfilePrefsUseCase,
) : SideEffect<ProfileStore.Action.SwitchPref, ProfileStore.SideAction> {

    override fun actionId() = ProfileStore.Action.SwitchPref::class.java

    override suspend fun execute(
        action: ProfileStore.Action.SwitchPref,
        reducerCallback: suspend (ProfileStore.SideAction) -> Unit
    ) {
        eventDispatcher.dispatch(ProfileStore.Event.ChangeAppThem(action.isOn))
        setPrefUseCase.execute(
            SetPrefUseCase.Query.SwitchPrefQuery(
                action.id,
                action.isOn
            )
        )
        val prefs = getProfilePrefsUseCase.execute()
        reducerCallback.invoke(ProfileStore.SideAction.UpdatePrefs(prefs))
    }
}
