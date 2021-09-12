package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.SideEffect
import ru.gubatenko.mvi.StateObservable

class LoadMainContentSideEffect(
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val stateObservable: StateObservable<MainStore.State>,
    private val getSuggestedActivityUseCase: GetSuggestedActivityUseCase
) : SideEffect<MainStore.Action.LoadContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: MainStore.Action.LoadContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            if (stateObservable.stateValue.isRefreshInProgress) {
                return
            }
            reducerCallback.invoke(MainStore.SideAction.LoadStart)
            val activity = getSuggestedActivityUseCase.execute()
            val saveButtonText = getStaticTextUseCase.execute("save")
            reducerCallback.invoke(MainStore.SideAction.LoadSuccess(activity = activity, saveButtonText = saveButtonText))
        } catch (e: Exception) {
            val message = getStaticTextUseCase.execute("error")
            val retryButtonText = getStaticTextUseCase.execute("retry")
            reducerCallback.invoke(MainStore.SideAction.LoadError(message = message, retryButtonText = retryButtonText))
        }
    }
}
