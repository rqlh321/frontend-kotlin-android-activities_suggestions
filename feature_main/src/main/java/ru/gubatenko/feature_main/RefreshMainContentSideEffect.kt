package ru.gubatenko.feature_main

import ru.gubatenko.core.EventDispatcher
import ru.gubatenko.core.SideEffect
import ru.gubatenko.domain.usecase.GreetingUseCase

class RefreshMainContentSideEffect(
    private val useCase: GreetingUseCase,
    private val eventDispatcher: EventDispatcher<MainStore.Event>
) : SideEffect<MainStore.Action.RefreshContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.RefreshContent::class.java

    override suspend fun execute(
        action: MainStore.Action.RefreshContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(MainStore.SideAction.RefreshStart)
            val activity = useCase.activity()
            reducerCallback.invoke(MainStore.SideAction.LoadSuccess(activity))
        } catch (e: Exception) {
            eventDispatcher.dispatch(MainStore.Event.ShowToast(e.message ?: "Undefined error"))
            reducerCallback.invoke(MainStore.SideAction.RefreshError)
        }
    }
}
