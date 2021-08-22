package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect
import ru.gubatenko.feature_main.MainStore

class ClickOnMainContentSideEffect(
    private val eventDispatcher: EventDispatcher<MainStore.Event>
) : SideEffect<MainStore.Action.ClickOnContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.ClickOnContent::class.java

    override suspend fun execute(
        action: MainStore.Action.ClickOnContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        eventDispatcher.dispatch(MainStore.Event.ShowToast("Toast!"))
    }
}
