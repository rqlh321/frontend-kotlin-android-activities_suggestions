package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.TextKey
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
            val saveButtonText = getStaticTextUseCase.execute(TextKey.Main.SAVE)
            reducerCallback.invoke(MainStore.SideAction.LoadSuccess(activity = activity, saveButtonText = saveButtonText))
        } catch (e: Exception) {
            val message = getStaticTextUseCase.execute(TextKey.Common.ERROR)
            val retryButtonText = getStaticTextUseCase.execute(TextKey.Common.RETRY)
            reducerCallback.invoke(MainStore.SideAction.LoadError(message = message, retryButtonText = retryButtonText))
        }
    }
}
