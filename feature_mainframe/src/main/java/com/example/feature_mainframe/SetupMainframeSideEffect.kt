package com.example.feature_mainframe

import ru.gubatenko.domain.DefinedPreference
import ru.gubatenko.mvi.SideEffect

class SetupMainframeSideEffect(
    private val preference: DefinedPreference
) : SideEffect<MainframeStore.Action.CheckSettingsAction, MainframeStore.SideAction> {

    override fun actionId() = MainframeStore.Action.CheckSettingsAction::class.java

    override suspend fun execute(
        action: MainframeStore.Action.CheckSettingsAction,
        reducerCallback: suspend (MainframeStore.SideAction) -> Unit
    ) {
        val isDarkThemEnabled = preference.isDarkThemEnabled()
        reducerCallback.invoke(MainframeStore.SideAction.SetupSideAction(isDarkThemEnabled))
    }
}
