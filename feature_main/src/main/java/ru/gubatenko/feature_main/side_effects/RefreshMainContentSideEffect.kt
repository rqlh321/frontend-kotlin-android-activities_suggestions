package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class RefreshMainContentSideEffect(
    private val useCase: GetSuggestedActivityUseCase,
    private val eventDispatcher: EventDispatcher<MainStore.Event>
) : SideEffect<MainStore.Action.RefreshContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.RefreshContent::class.java

    override suspend fun execute(
        action: MainStore.Action.RefreshContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(MainStore.SideAction.RefreshStart)
            val activity = useCase.execute()
            reducerCallback.invoke(MainStore.SideAction.RefreshSuccess(activity))
        } catch (e: Exception) {
            eventDispatcher.dispatch(MainStore.Event.ShowToast(e.message ?: "Undefined error"))
            reducerCallback.invoke(MainStore.SideAction.RefreshError)
        }
    }
}
