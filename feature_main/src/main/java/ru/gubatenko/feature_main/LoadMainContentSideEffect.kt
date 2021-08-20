package ru.gubatenko.feature_main

import ru.gubatenko.core.SideEffect
import ru.gubatenko.domain.usecase.GreetingUseCase

class LoadMainContentSideEffect(
    private val useCase: GreetingUseCase
) : SideEffect<MainStore.Action.LoadContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: MainStore.Action.LoadContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(MainStore.SideAction.LoadStart)
            val activity = useCase.activity()
            reducerCallback.invoke(MainStore.SideAction.LoadSuccess(activity))
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
