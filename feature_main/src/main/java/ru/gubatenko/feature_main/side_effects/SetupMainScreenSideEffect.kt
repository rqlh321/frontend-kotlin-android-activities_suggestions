package ru.gubatenko.feature_main.side_effects

import com.example.audit.Logger
import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.SideEffect

class SetupMainScreenSideEffect(
    private val logger: Logger,
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val getSuggestedActivityUseCase: GetSuggestedActivityUseCase,
) : SideEffect<MainStore.Action.SetupScreen, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.SetupScreen::class.java

    override suspend fun execute(
        action: MainStore.Action.SetupScreen,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(MainStore.SideAction.LoadStart)
            val activity = getSuggestedActivityUseCase.execute()
            val saveButtonText = getStaticTextUseCase.execute(TextKey.Main.SAVE)
            reducerCallback.invoke(MainStore.SideAction.LoadSuccess(activity = activity, saveButtonText = saveButtonText))
        } catch (e: Exception) {
            logger.d(e)
            val message = getStaticTextUseCase.execute(TextKey.Common.ERROR)
            val retryButtonText = getStaticTextUseCase.execute(TextKey.Common.RETRY)
            reducerCallback.invoke(MainStore.SideAction.LoadError(message = message, retryButtonText = retryButtonText))
        }
    }
}
