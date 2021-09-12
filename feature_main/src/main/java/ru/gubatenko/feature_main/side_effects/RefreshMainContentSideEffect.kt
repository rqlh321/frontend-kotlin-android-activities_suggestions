package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.EventDispatcher
import ru.gubatenko.mvi.SideEffect

class RefreshMainContentSideEffect(
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val getSuggestedActivityUseCase: GetSuggestedActivityUseCase,
    private val eventDispatcher: EventDispatcher<MainStore.Event>
) : SideEffect<MainStore.Action.RefreshContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.RefreshContent::class.java

    override suspend fun execute(
        action: MainStore.Action.RefreshContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(MainStore.SideAction.RefreshStart)
            val activity = getSuggestedActivityUseCase.execute()
            reducerCallback.invoke(MainStore.SideAction.RefreshSuccess(activity))
        } catch (e: Exception) {
            val message = getStaticTextUseCase.execute("error")
            eventDispatcher.dispatch(MainStore.Event.ShowToast(message))
            reducerCallback.invoke(MainStore.SideAction.RefreshError)
        }
    }
}
