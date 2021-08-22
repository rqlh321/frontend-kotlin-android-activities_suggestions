package ru.gubatenko.feature_main.side_effects

import ru.gubatenko.mvi.SideEffect
import ru.gubatenko.domain.exception.UnknownUserException
import ru.gubatenko.domain.usecase.ActivityUseCase
import ru.gubatenko.feature_main.MainStore

class ClickOnSaveSideEffect(
    private val useCase: ActivityUseCase
) : SideEffect<MainStore.Action.SaveContent, MainStore.SideAction> {

    override fun actionId() = MainStore.Action.SaveContent::class.java

    override suspend fun execute(
        action: MainStore.Action.SaveContent,
        reducerCallback: suspend (MainStore.SideAction) -> Unit
    ) {
        val actionToSave = action.action ?: return
        useCase.save(actionToSave)
        throw UnknownUserException()
    }
}
