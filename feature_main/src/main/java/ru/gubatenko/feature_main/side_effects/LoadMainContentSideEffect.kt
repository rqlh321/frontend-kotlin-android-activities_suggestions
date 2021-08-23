package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.SideEffect
import ru.gubatenko.mvi.StateObservable

class LoadMainContentSideEffect(
    private val state: StateObservable<MainStore.State>,
    private val useCase: ActivityUseCase
) : SideEffect<MainStore.Action.LoadContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: MainStore.Action.LoadContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            if (state.stateValue.isRefreshInProgress) {
                return
            }
            reducerCallback.invoke(MainStore.SideAction.LoadStart)
            val activity = useCase.activity()
            reducerCallback.invoke(
                MainStore.SideAction.LoadSuccess(
                    saveButtonText = "Save",
                    activity = activity
                )
            )
        } catch (e: Exception) {
            reducerCallback.invoke(
                MainStore.SideAction.LoadError(
                    retryButtonText = "Retry",
                    errorMessageText = e.message ?: "Undefined error"
                )
            )
        }
    }
}
