package ru.gubatenko.feature_main

import ru.gubatenko.core.EventDispatcher
import ru.gubatenko.core.SideEffect

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
