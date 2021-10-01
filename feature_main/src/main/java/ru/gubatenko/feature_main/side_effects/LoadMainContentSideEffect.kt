package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.MainStore
import ru.gubatenko.mvi.SideEffect

class LoadMainContentSideEffect(
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val getSuggestedActivityUseCase: GetSuggestedActivityUseCase
) : SideEffect<MainStore.Action.LoadContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: MainStore.Action.LoadContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(MainStore.SideAction.LoadStart)
            val idea = getSuggestedActivityUseCase.execute()
            val saveButtonText = getStaticTextUseCase.execute(TextKey.Main.SAVE)
            val nextButtonText = getStaticTextUseCase.execute(TextKey.Main.NEXT)
            reducerCallback.invoke(
                MainStore.SideAction.LoadSuccess(
                    idea = idea,
                    saveButtonText = saveButtonText,
                    nextButtonText = nextButtonText
                )
            )
        } catch (e: Exception) {
            val message = getStaticTextUseCase.execute(TextKey.Common.ERROR)
            val retryButtonText = getStaticTextUseCase.execute(TextKey.Common.RETRY)
            reducerCallback.invoke(
                MainStore.SideAction.LoadError(
                    message = message,
                    retryButtonText = retryButtonText
                )
            )
        }
    }
}
