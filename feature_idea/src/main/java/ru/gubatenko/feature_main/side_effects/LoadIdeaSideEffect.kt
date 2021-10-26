package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.domain.TextKey
import ru.gubatenko.domain.usecase.GetStaticTextUseCase
import ru.gubatenko.domain.usecase.GetSuggestedActivityUseCase
import ru.gubatenko.feature_main.IdeaStore
import ru.gubatenko.mvi.SideEffect

class LoadIdeaSideEffect(
    private val getStaticTextUseCase: GetStaticTextUseCase,
    private val getSuggestedActivityUseCase: GetSuggestedActivityUseCase
) : SideEffect<IdeaStore.Action.LoadContent, IdeaStore.SideAction> {

    override fun actionId() = IdeaStore.Action.LoadContent::class.java

    override suspend fun execute(
        action: IdeaStore.Action.LoadContent,
        reducerCallback: suspend (IdeaStore.SideAction) -> Unit
    ) {
        try {
            reducerCallback.invoke(IdeaStore.SideAction.LoadStart)
            val idea = getSuggestedActivityUseCase.execute()
            val saveButtonText = getStaticTextUseCase.execute(TextKey.Main.SAVE)
            val nextButtonText = getStaticTextUseCase.execute(TextKey.Main.NEXT)
            reducerCallback.invoke(
                IdeaStore.SideAction.LoadSuccess(
                    idea = idea,
                    saveButtonText = saveButtonText,
                    nextButtonText = nextButtonText
                )
            )
        } catch (e: Exception) {
            val message = getStaticTextUseCase.execute(TextKey.Common.ERROR)
            val retryButtonText = getStaticTextUseCase.execute(TextKey.Common.RETRY)
            reducerCallback.invoke(
                IdeaStore.SideAction.LoadError(
                    message = message,
                    retryButtonText = retryButtonText
                )
            )
        }
    }
}
